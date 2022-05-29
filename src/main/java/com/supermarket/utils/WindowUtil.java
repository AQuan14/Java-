package com.supermarket.utils;

import java.awt.*;

public class WindowUtil {
	 //���ô������
    public static void setFrameCenter(Container c) {
        //��ȡ���߶���
        Toolkit tk = Toolkit.getDefaultToolkit();

        //��ȡ��Ļ�Ŀ�͸�
        Dimension d = tk.getScreenSize();
        double srceenWidth = d.getWidth();
        double srceenHeigth = d.getHeight();

        //��ȡ����Ŀ�͸�
        int frameWidth = c.getWidth();
        int frameHeight = c.getHeight();

        //��ȡ�µĿ�͸�
        int width = (int) (srceenWidth - frameWidth) / 2;
        int height = (int) (srceenHeigth - frameHeight) / 2;

        //���ô�������
        c.setLocation(width, height);
    }
}
