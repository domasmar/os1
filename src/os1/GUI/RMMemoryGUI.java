package os1.GUI;

import os1.Memory.RMMemory;

public class RMMemoryGUI extends TableGUI {

	private RMMemory rmm;

	public RMMemoryGUI(RMMemory rmm) {
		super("RM atmintis", 200);
		this.rmm = rmm;
		this.setColumnNames(new String[] {"Adresas", "Reikðmë"});
		super.initList();
	}

	@Override
	protected int getInfo(int i) {
		return rmm.getValue(i);
	}

	@Override
	protected int getSize() {
		return rmm.getSize();
	}

	@Override
	protected void setValue(int index, int value) {
		rmm.setValue(index, value);
	}

	@Override
	protected String getName(int index) {
		return ""+index;
	}
}
