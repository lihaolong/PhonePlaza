package model;

import java.util.Date;

public class PhoneInfo {
	private String phoneName;
	private String phoneBrand;
	private int price;
	private Date sellTime;
	private float screenSize;
	private String screenPx;
	private String screenMaterial;
	private String weight;
	private String thickness;
	private String ram;
	private String rom;
	private String battery;
	private boolean touchID;
	private String nettype;
	private CpuInfo cpuInfo;
	private CameraInfo cameraInfo;

	public CameraInfo getCameraInfo() {
		return cameraInfo;
	}

	public void setCameraInfo(CameraInfo cameraInfo) {
		this.cameraInfo = cameraInfo;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getSellTime() {
		return sellTime;
	}

	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}

	public float getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(float screenSize) {
		this.screenSize = screenSize;
	}

	public String getScreenPx() {
		return screenPx;
	}

	public void setScreenPx(String screenPx) {
		this.screenPx = screenPx;
	}

	public String getScreenMaterial() {
		return screenMaterial;
	}

	public void setScreenMaterial(String screenMaterial) {
		this.screenMaterial = screenMaterial;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getRom() {
		return rom;
	}

	public void setRom(String rom) {
		this.rom = rom;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public boolean isTouchID() {
		return touchID;
	}

	public void setTouchID(boolean touchID) {
		this.touchID = touchID;
	}

	public String getNettype() {
		return nettype;
	}

	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

	public CpuInfo getCpuInfo() {
		return cpuInfo;
	}

	public void setCpuInfo(CpuInfo cpuInfo) {
		this.cpuInfo = cpuInfo;
	}
}
