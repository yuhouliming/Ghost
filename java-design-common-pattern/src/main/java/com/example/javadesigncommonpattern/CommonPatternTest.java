package com.example.javadesigncommonpattern;

import javax.sound.midi.Soundbank;

/**
 * 抽象命令类（Command）角色：声明执行命令的接口，拥有执行命令的抽象方法 execute()。
 具体命令角色（Concrete    Command）角色：是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作。
 实现者/接收者（Receiver）角色：执行命令功能的相关操作，是具体命令对象业务的真正实现者。
 调用者/请求者（Invoker）角色：是请求的发送者，它通常拥有很多的命令对象，并通过访问命令对象来执行相关请求，它不直接访问接收者。
 */
public class CommonPatternTest {
    public static void main(String[] args) {
        ICommand iCommand = new CommandImpl();
        Invoker invoker = new Invoker(iCommand);
        System.out.println("我是客户端，我开始调用了");
        invoker.call();
    }
}

//定义抽象命令
interface ICommand{
    //定义一个抽象方法
    void excuteCommand();
}
//定义具体命令。这里可以组合各种命令
class CommandImpl implements ICommand{
    private Receiver receiver;
    CommandImpl() {
        receiver = new Receiver();
    }

    @Override
    public void excuteCommand() {
        receiver.receiverMethod();
        System.out.println("我是具体命令，我实现了抽象命令");
    }
}
//接受者，实现具体业务
class Receiver{
    public void receiverMethod(){
        System.out.println("我是接受者，我负责执行命令");
    }
}
class Invoker{
    private ICommand iCommand;
    public Invoker(ICommand command)
    {
        iCommand = new CommandImpl();
    }
    public void call()
    {
        System.out.println("我是调用者，我调用命令");
        iCommand.excuteCommand();
    }
}