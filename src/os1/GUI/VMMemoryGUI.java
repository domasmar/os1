package os1.GUI;

import os1.Memory.VMMemory;

public class VMMemoryGUI extends MemoryGUI {

	private VMMemory vmm;
	
	public VMMemoryGUI(VMMemory vmm) {
		super("VM Memory", 400);
		this.vmm = vmm;
		super.initList();
		super.setVisible();
	}

	@Override
	protected String getInfo(int i) {
		return "" + vmm.getValue(i);
	}

	@Override
	protected int getSize() {
		return vmm.getSize();
	}
}
