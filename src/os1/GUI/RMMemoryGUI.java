package os1.GUI;

import os1.Memory.RMMemory;

public class RMMemoryGUI extends MemoryGUI {

	private RMMemory rmm;

	public RMMemoryGUI(RMMemory rmm) {
		super("RM Memory", 200);
		this.rmm = rmm;
		super.initList();
		super.setVisible();
	}

	@Override
	protected String getInfo(int i) {
		return "" + rmm.getValue(i);
	}

	@Override
	protected int getSize() {
		return rmm.getSize();
	}
}
