package os1.GUI;

import os1.Memory.VMMemory;

public class VMMemoryGUI extends TableGUI {

	private VMMemory vmm;
	
	public VMMemoryGUI(VMMemory vmm) {
		super("VM Memory", 200);
		this.vmm = vmm;
		this.setColumnNames(new String[] {"Value", "Adress"});
		super.initList();
	}

	@Override
	protected int getInfo(int i) {
		return vmm.getValue(i);
	}

	@Override
	protected int getSize() {
		return vmm.getSize();
	}

	@Override
	protected void setValue(int index, int value) {
		vmm.setValue(index, value);		
	}

	@Override
	protected String getName(int index) {
		return "" + index;
	}
}
