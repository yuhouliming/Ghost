package com.example.dockerdemo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class V3ToWav {
    private static V3ToWav v3ToWav;
    private V3ToWav(){

    }

    public static V3ToWav getInstance(){
        if(v3ToWav == null){
            v3ToWav = new V3ToWav();
        }
        return v3ToWav;
    }

    private static final int WAV_HEAD = 36;// wav文件头长度

    private static final int VF_ADPCM = 1;// 编码格式

    private static final int BIT_RATE_VB_8 = 1;// 每个样本位数

    private static final int BIT_RATE_VB_16 = 2;// 每个样本位数

    private static final int RESET_VALUE = 48;

    private static byte out_val;

    /**
     *
     * @param inFile
     * @param outFile
     * @param voxFormat
     *            格式 取值范围:VF_ADPCM = 1, VF_MULAW = 2, VF_ALAW = 3
     * @param voxRate
     *            采样率 取值范围：VR_6K = 6000, VR_8K = 8000
     * @param bit_rate
     *            位数 取值范围：VB_8 = 1, VB_16 = 2
     * @return
     */

    public static void voxConvert(String inFile, String outFile, int voxFormat,
                                  int voxRate, int bit_rate) throws Exception {

        if (outFile == null) {
            outFile = inFile.substring(0, inFile.length() - 2);
            outFile = outFile + "wav";
        }

        File outF = new File(outFile);
        File inF = new File(inFile);

        long inFileSize = inF.length();
        if (voxFormat == VF_ADPCM) { // if using ADPCM input format...
            inFileSize = inFileSize * 2;
        }// change from bytes to samples

        FileOutputStream filewriter = new FileOutputStream(outF, false);
        String wavBegin = "RIFF";
        filewriter.write(wavBegin.getBytes());// WAV 文件头

        long wavLength = inFileSize * bit_rate + WAV_HEAD;
        byte[] tmpArr = new byte[4];
        longToIntBinary(wavLength, tmpArr, 0);
        filewriter.write(tmpArr);// 文件总长度

        String wavTag = "WAVEfmt ";
        filewriter.write(wavTag.getBytes());// WAV 文件标识

        int headLength = 16;
        tmpArr = new byte[4];
        longToIntBinary(headLength, tmpArr, 0);
        filewriter.write(tmpArr);// size of .WAV file header

        int wFormatTag = 1; // format tag (01 = Windows PCM)
        tmpArr = new byte[2];
        toShortBinary(wFormatTag, tmpArr, 0);
        filewriter.write(tmpArr);// format tag (01 = Windows PCM)

        int nChannels = 1; // channels (1=mono, 2=stereo)
        tmpArr = new byte[2];
        toShortBinary(nChannels, tmpArr, 0);
        filewriter.write(tmpArr);// channels (1=mono, 2=stereo)

        int nSamplesPerSec = voxRate; // samples per second
        tmpArr = new byte[4];
        longToIntBinary(nSamplesPerSec, tmpArr, 0);
        filewriter.write(tmpArr);

        int nAvgBytesPerSec = voxRate * bit_rate; // bytes per second
        // during play
        tmpArr = new byte[4];
        longToIntBinary(nAvgBytesPerSec, tmpArr, 0);
        filewriter.write(tmpArr);

        int nBlockAlign = bit_rate; // bytes per sample
        tmpArr = new byte[2];
        toShortBinary(nBlockAlign, tmpArr, 0);
        filewriter.write(tmpArr);// bytes per sample

        int wBitsPerSample = 8 * bit_rate; // bits per sample
        tmpArr = new byte[2];
        toShortBinary(wBitsPerSample, tmpArr, 0);
        filewriter.write(tmpArr);// bits per sample

        /** ******以下是数据头********* */
        String dataTag = "data";
        filewriter.write(dataTag.getBytes());// data tag

        long Datalen = inFileSize * bit_rate; // write size of .WAV data
        // portion
        tmpArr = new byte[4];
        longToIntBinary(Datalen, tmpArr, 0);
        filewriter.write(tmpArr);// 数据总长度

        FileInputStream fileReader = new FileInputStream(inF);
        byte[] outbytebuffer = new byte[20000];
        int[] outintbuffer = new int[20000];
        switch (voxFormat) {
            case VF_ADPCM:// VF_ADPCM
            {
                short Sn = 0; // initialize the ADPCM variables
                int SS = 16; // initialize the Step
                int SSindex = 1;
                int i = 0;
                byte[] b = new byte[10000];

                int outindex;
                byte sample;// 一个采样样本

                int allBytes = fileReader.available();
                while (allBytes > 0) {
                    int thisRead = allBytes > 10000 ? 10000 : allBytes;
                    int bytes = fileReader.read(b, 0, thisRead);

                    allBytes -= thisRead;// 剩余可读的字节数
                    outindex = 0;
                    for (int index = 0; index < bytes; index++) {
                        sample = b[index];
                        byte highByte = (byte) (sample >>> 4 & 0xff);
                        byte lowByte = (byte) (sample & 0x0f);

                        /** *****开始高字节处理******** */
                        if ((highByte == 0) || (highByte == 8)) {
                            i++;
                        }
                        Object[] retVal = decode((byte) (sample >>> 4), Sn, SS,
                                SSindex);
                        Sn = ((Short) retVal[0]).shortValue();
                        SS = ((Integer) retVal[1]).intValue();
                        SSindex = ((Integer) retVal[2]).intValue();

                        int out_int;
                        if (bit_rate == BIT_RATE_VB_8) {// if 8 bits per sample...
                            out_int = Sn / 16;
                            if (out_int > 127) { // clip if above or below WAV
                                // bounds
                                out_int = 127;
                            }
                            if (out_int < -128) {
                                out_int = -128;
                            }
                            out_val = (byte) ((out_int - 128) & 0xff); // convert

                            // to
                            // .WAV format
                            outbytebuffer[outindex] = out_val; // write the output
                            // byte
                        } else {// else 16 bits per sample
                            out_int = Sn * 16; // rescale to 16 bits
                            outintbuffer[outindex] = out_int; // write the output
                            // int
                        }
                        outindex++;

                        if (i == RESET_VALUE) { // Reset ADPCM variables 48位时重置各个值
                            Sn = 0; // initialize the ADPCM variables
                            SS = 16; // initialize the Step
                            i = 0;
                        }

                        /** *******低字节处理********* */
                        if (lowByte == 0 || lowByte == 8) {
                            i++;
                        }
                        retVal = decode((byte) (sample & 0x0f), Sn, SS, SSindex);
                        Sn = ((Short) retVal[0]).shortValue();
                        SS = ((Integer) retVal[1]).intValue();
                        SSindex = ((Integer) retVal[2]).intValue();

                        if (bit_rate == BIT_RATE_VB_8) {// if 8 bits per sample...
                            out_int = Sn / 16;
                            if (out_int > 127) { // clip if above or below WAV
                                // bounds
                                out_int = 127;
                            }
                            if (out_int < -128) {
                                out_int = -128;
                            }
                            out_val = (byte) ((out_int - 128) & 0xff); // convert

                            // to
                            // .WAV format
                            outbytebuffer[outindex] = out_val; // write the output
                            // byte
                        } else {// else 16 bits per sample
                            out_int = Sn * 16; // rescale to 16 bits
                            outintbuffer[outindex] = out_int; // write the output
                            // int
                        }
                        outindex++;

                        if (i == RESET_VALUE) { // Reset ADPCM variables 48位时重置各个值
                            Sn = 0; // initialize the ADPCM variables
                            SS = 16; // initialize the Step
                            i = 0;
                        }
                    }
                    if (bit_rate == BIT_RATE_VB_8) {
                        filewriter.write(outbytebuffer);

                    } else {
                        for (int j = 0; j < outintbuffer.length; j++) {
                            byte[] arr = new byte[4];
                            longToIntBinary(outintbuffer[j], arr, 0);
                            filewriter.write(arr);

                        }
                    }
                }
                break;

            }
            default:
                break;
        }

        fileReader.close();
        filewriter.close();

    }

    /**
     *
     * @param sample
     * @param Sn
     * @param ss
     *            引用变量
     * @param SSindex
     *            引用变量
     * @return
     */
    private static Object[] decode(byte sample, short Sn, int SS, int SSindex) {
        Object[] retArr = new Object[3];
        int[] SSadjust = new int[] { -1, -1, -1, -1, 2, 4, 6, 8 };
        // Calculated stepsizes per Dialogic Application Note 1366
        int[] SStable = new int[] { 0, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37,
                41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143,
                157, 173, 190, 209, 230, 253, 279, 307, 337, 371, 408, 449,
                494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411,
                1552 };
        int Mn = 0; // calculate the linear adjustment
        if ((sample & 0x4) != 0) {
            Mn = SS;
        }
        if ((sample & 0x2) != 0) {
            Mn = Mn + (SS >>> 1);
        }// div 2
        if ((sample & 0x1) != 0) {
            Mn = Mn + (SS >>> 2);
        }// div 4

        Mn = Mn + (SS >>> 3); // div 8
        // 取Sample的符号位，即最高位
        if ((sample & 0x8) != 0) { // 最高位为1，则符号位为负
            Sn = (short) (((int) Sn - Mn) & 0xffff); // ...subtract the
            // adjustment
        } else { // 符号位为正
            Sn = (short) (((int) Sn + Mn) & 0xffff); // ...add the adjustment
        }

        if (Sn > 2047) { // adjust if sample too large...
            Sn = 2047;
        }
        if (Sn < -2048) { // ...or too small
            Sn = -2048;
        }

        // use as index into step size adjustment, adjust step size index
        SSindex = SSindex + SSadjust[sample & 0x07];

        if (SSindex < 1) { // keep SSindex within bounds...
            SSindex = 1;
        }
        if (SSindex > 49) {
            SSindex = 49;
        }

        SS = SStable[SSindex]; // get new step size from table

        retArr[0] = Sn;
        retArr[1] = SS;
        retArr[2] = SSindex;
        return retArr;
    }

    /**
     * 整型转数组
     *
     * @param val
     * @param array
     * @param offset
     */
    private static void longToIntBinary(long val, byte[] array, int offset) {
        array[offset] = (byte) (val & 0xff);
        array[offset + 1] = (byte) (val >>> 8 & 0xff);
        array[offset + 2] = (byte) (val >>> 16 & 0xff);
        array[offset + 3] = (byte) (val >>> 24 & 0xff);
    }

    private void byteToShortBinary(byte val, byte[] array, int offset) {
        array[offset] = (byte) (val & 0xff);
        array[offset + 1] = 0x0;
    }

    /**
     * java中没有有符号类型，所以将超过0x7FFF的short类型保存为int类型。本方法提供了将有符号short类型
     * 转换保存在字节数组中，占据两个字节
     *
     * @param val
     *            int
     * @param array
     *            byte[]
     * @param offset
     *            int
     */
    private static void toShortBinary(int val, byte[] array, int offset) {
        array[offset] = (byte) (val & 0xff);
        array[offset + 1] = (byte) (val >>> 8 & 0xff);
    }

    public static void main(String[] args) {

        String inFile  = "C:\\Users\\lmwu5\\Desktop\\ningbo\\vox\\4bit16k.vox";
        String outFile = "C:\\Users\\lmwu5\\Desktop\\ningbo\\vox\\9532.wav";

        try {
            voxConvert(inFile, outFile, 1, 8000, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
