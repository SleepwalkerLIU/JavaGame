package com.zzk.interpreter;

import java.awt.Graphics;
import java.awt.Point;

import com.zzk.AbstractGrammarInterpreter;
import com.zzk.util.DrawUtil;
/**
 * ������F
 * @author zzk
 */
public class GrammarInterpreterF extends AbstractGrammarInterpreter implements Cloneable{
	private boolean isSymmetricalTree = false;
	public GrammarInterpreterF() {
		super();
	}

	public GrammarInterpreterF(int length, String grammarString, Point startPoint, double startAngle, double rotateAngle) {
		super(length, grammarString, startPoint, startAngle, rotateAngle);
		if (length >= 100)
			isSymmetricalTree = true;// ����Ϊ100���򿪳����Լ�����
	}
	/**
	 * ����ʽ�ӽ��н��ͣ���ͼ
	 * @param g
	 */
	@Override
	public void interpret(Graphics g) {
		if(grammarString==null) return;
		for (char ch : grammarString.toCharArray()) {
			switch (ch) {
			case 'F':// ����
				startPoint = DrawUtil.drawLine(g, startPoint, startAngle, length);
				break;
			case '+':// ��תrotateAngle��
				startAngle += rotateAngle;
				break;
			case '-':// ��תrotateAngle��
				startAngle -= rotateAngle;
				break;
			case '[':// ���浱ǰ״̬
				try {
					stack.push((GrammarInterpreterF) this.clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				if (isSymmetricalTree)
					length -= 10;// �ԳƷ�����ʱ��
				break;
			case ']':// �ص���һ��
				GrammarInterpreterF d = (GrammarInterpreterF) stack.pop();
				this.startPoint = d.startPoint;
				this.startAngle = d.startAngle;
				this.length = d.length;
				break;
			}
		}
	}
}
