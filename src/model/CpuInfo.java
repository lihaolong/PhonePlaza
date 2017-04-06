package model;

public class CpuInfo {
	private String cpuName;
	private int cpuCoreNum;
	private String cpuHz;
	private String gpu;
	private String cpuProcess;

	public String getCpuName() {
		return cpuName;
	}

	public void setCpuName(String cpuName) {
		this.cpuName = cpuName;
	}

	public int getCpuCoreNum() {
		return cpuCoreNum;
	}

	public void setCpuCoreNum(int cpuCoreNum) {
		this.cpuCoreNum = cpuCoreNum;
	}

	public String getCpuHz() {
		return cpuHz;
	}

	public void setCpuHz(String cpuHz) {
		this.cpuHz = cpuHz;
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getCpuProcess() {
		return cpuProcess;
	}

	public void setCpuProcess(String cpuProcess) {
		this.cpuProcess = cpuProcess;
	}
}
