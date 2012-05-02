package com.vaadin.demo.jpaaddressbook;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class JpaAddressbookApplication extends Application {

    public static final String PERSISTENCE_UNIT = "addressbook";

    static {
    	System.out.println("BEFORE DEMODATAGENERATOR");
        DemoDataGenerator.create();
        System.out.println("AFTER DEMODATAGENERATOR");
    }

    @Override
    public void init() {
        Window window = new Window();
        setMainWindow(window);
        window.setContent(new AddressBookMainView());
    }

}
