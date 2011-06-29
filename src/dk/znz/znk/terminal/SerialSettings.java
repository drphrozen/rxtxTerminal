package dk.znz.znk.terminal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SerialSettings {
  private String                mPort;
  private String                mSpeed;
  private String                mDataBits;
  private String                mStopBits;
  private String                mParity;
  private String                mFlowControl;

  private PropertyChangeSupport mPropertyChangeSupport = new PropertyChangeSupport(this);

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    mPropertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    mPropertyChangeSupport.removePropertyChangeListener(listener);
  }

  public SerialSettings() {}

  public SerialSettings(String speed, String dataBits, String stopBits, String parity, String flowControl) {
    mSpeed = speed;
    mDataBits = dataBits;
    mStopBits = stopBits;
    mParity = parity;
    mFlowControl = flowControl;
  }

  public SerialSettings(SerialSettings settings) {
    mSpeed = settings.mSpeed;
    mDataBits = settings.mDataBits;
    mStopBits = settings.mStopBits;
    mParity = settings.mParity;
    mFlowControl = settings.mFlowControl;
  }

  public String getSpeed() {
    return mSpeed;
  }

  public void setSpeed(String speed) {
    mSpeed = speed;
  }

  public String getDataBits() {
    return mDataBits;
  }

  public void setDataBits(String dataBits) {
    mDataBits = dataBits;
  }

  public String getStopBits() {
    return mStopBits;
  }

  public void setStopBits(String stopBits) {
    mStopBits = stopBits;
  }

  public String getParity() {
    return mParity;
  }

  public void setParity(String parity) {
    mParity = parity;
  }

  public String getFlowControl() {
    return mFlowControl;
  }

  public void setFlowControl(String flowControl) {
    mFlowControl = flowControl;
  }

  public String getPort() {
    return mPort;
  }

  public void setPort(String port) {
    mPort = port;
  }
}
