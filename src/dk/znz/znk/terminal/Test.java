package dk.znz.znk.terminal;

import gnu.io.DataBits;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;

public class Test {

  protected Shell mshell;

  /**
   * Launch the application.
   * @param args
   */
  public static void main(String[] args) {
    try {
      Test window = new Test();
      window.open();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Open the window.
   */
  public void open() {
    Display display = Display.getDefault();
    createContents();
    mshell.open();
    mshell.layout();
    while (!mshell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  /**
   * Create contents of the window.
   */
  protected void createContents() {
    mshell = new Shell();
    mshell.setSize(450, 300);
    mshell.setText("SWT Application");
    mshell.setLayout(new FillLayout(SWT.HORIZONTAL));
    
    ComboEnum comboEnum = new ComboEnum(mshell, SWT.NONE, DataBits.class, DataBits.DATABITS_8, true);

  }
}
