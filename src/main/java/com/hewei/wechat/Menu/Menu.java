package com.hewei.wechat.Menu;

public class Menu {
	/* 定义名称与json中一致，不然解析名称对不上 */
	private MenuButton[] button;

	public MenuButton[] getButton() {
		return button;
	}

	public void setButton(MenuButton[] button) {
		this.button = button;
	}
}
