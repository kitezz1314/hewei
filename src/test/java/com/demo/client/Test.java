package com.demo.client;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InvokerImplService invokerImplService = new InvokerImplService();
		Invoker invoker = invokerImplService.getInvokerImplPort();
		System.out.println(invoker.getData("00"));
	}

}
