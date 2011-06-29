package dk.znz.znk.terminal;

import gnu.io.DataBits;
import gnu.io.Utils;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SettingsDialog extends Dialog {

  private DataBindingContext mBindingContext;
  private SerialSettings     mSerialSettings = new SerialSettings();
  private Combo              mPortCombo;
  private Combo              mSpeedCombo;
  private ComboEnum          mDataBitsCombo;
  private Combo              mParityCombo;
  private Combo              mStopBitsCombo;
  private Combo              mFlowControlCombo;

  /**
   * @wbp.parser.constructor
   */
  public SettingsDialog(Shell parentShell) {
    super(parentShell);
  }

  public SettingsDialog(Shell parentShell, SerialSettings newSerialSettings) {
    super(parentShell);
    setSerialSettings(newSerialSettings, false);
  }

  /**
   * Create contents of the dialog.
   * 
   * @param parent
   */
  @Override protected Control createDialogArea(Composite parent) {
    Composite container = (Composite) super.createDialogArea(parent);
    container.setLayout(new GridLayout(2, false));

    new Label(container, SWT.NONE).setText("Port:");

    mPortCombo = new Combo(container, SWT.BORDER);
    mPortCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(container, SWT.NONE).setText("Speed:");

    mSpeedCombo = new Combo(container, SWT.BORDER);
    mSpeedCombo.setItems(Utils.DefaultSpeeds);
    mSpeedCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(container, SWT.NONE).setText("DataBits:");

    mDataBitsCombo = new ComboEnum(container, SWT.BORDER, DataBits.class);
    mDataBitsCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(container, SWT.NONE).setText("Parity:");

    mParityCombo = new Combo(container, SWT.BORDER);
    mParityCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(container, SWT.NONE).setText("StopBits:");

    mStopBitsCombo = new Combo(container, SWT.BORDER);
    mStopBitsCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    new Label(container, SWT.NONE).setText("FlowControl:");

    mFlowControlCombo = new Combo(container, SWT.BORDER);
    mFlowControlCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    if (mSerialSettings != null) {
      mBindingContext = initDataBindings();
    }
    return container;
  }

  /**
   * Create contents of the button bar.
   * 
   * @param parent
   */
  @Override protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
  }

  /**
   * Return the initial size of the dialog.
   */
  @Override protected Point getInitialSize() {
    return new Point(450, 300);
  }

  private DataBindingContext initDataBindings() {
    IObservableValue portObserveWidget = SWTObservables.observeSelection(mPortCombo);
    IObservableValue portObserveValue = BeansObservables.observeValue(mSerialSettings, "port");
    IObservableValue speedObserveWidget = SWTObservables.observeSelection(mSpeedCombo);
    IObservableValue speedObserveValue = BeansObservables.observeValue(mSerialSettings, "speed");
    IObservableValue dataBitsObserveWidget = SWTObservables.observeSelection(mDataBitsCombo);
    IObservableValue dataBitsObserveValue = BeansObservables.observeValue(mSerialSettings, "dataBits");
    IObservableValue parityObserveWidget = SWTObservables.observeSelection(mParityCombo);
    IObservableValue parityObserveValue = BeansObservables.observeValue(mSerialSettings, "parity");
    IObservableValue stopBitsObserveWidget = SWTObservables.observeSelection(mStopBitsCombo);
    IObservableValue stopBitsObserveValue = BeansObservables.observeValue(mSerialSettings, "stopBits");
    IObservableValue flowControlObserveWidget = SWTObservables.observeSelection(mFlowControlCombo);
    IObservableValue flowControlObserveValue = BeansObservables.observeValue(mSerialSettings, "flowControl");
    //
    DataBindingContext bindingContext = new DataBindingContext();
    //
    bindingContext.bindValue(portObserveWidget, portObserveValue, null, null);
    bindingContext.bindValue(speedObserveWidget, speedObserveValue, null, null);
    bindingContext.bindValue(dataBitsObserveWidget, dataBitsObserveValue, null, null);
    bindingContext.bindValue(parityObserveWidget, parityObserveValue, null, null);
    bindingContext.bindValue(stopBitsObserveWidget, stopBitsObserveValue, null, null);
    bindingContext.bindValue(flowControlObserveWidget, flowControlObserveValue, null, null);
    //
    return bindingContext;
  }

  public dk.znz.znk.terminal.SerialSettings getSerialSettings() {
    return mSerialSettings;
  }

  public void setSerialSettings(dk.znz.znk.terminal.SerialSettings newSerialSettings) {
    setSerialSettings(newSerialSettings, true);
  }

  public void setSerialSettings(dk.znz.znk.terminal.SerialSettings newSerialSettings, boolean update) {
    mSerialSettings = newSerialSettings;
    if (update) {
      if (mBindingContext != null) {
        mBindingContext.dispose();
        mBindingContext = null;
      }
      if (mSerialSettings != null) {
        mBindingContext = initDataBindings();
      }
    }
  }

}
