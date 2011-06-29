package dk.znz.znk.terminal;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class ComboEnum extends Composite {

  private final HashMap<String, ? extends Enum<?>> mEnumMap;
  private final Combo mCombo;
  
  /**
   * Create the composite.
   * @param parent
   * @param style
   */
  public ComboEnum(Composite parent, int style, Class<? extends Enum<?>> c, Enum<?> e, boolean readOnly) {
    super(parent, style);
    Enum<?>[] constants = c.getEnumConstants();
    mEnumMap = createMapFromEnum(constants);

    setLayout(new FillLayout(SWT.HORIZONTAL));

    mCombo = new Combo(this, readOnly ? SWT.READ_ONLY : SWT.NONE);
    mCombo.setItems(valuesOf(constants));
    if(e != null) {
      mCombo.select(findEnumIndex(constants, e));
    }
  }
  
  private final static <T extends Enum<?>> HashMap<String, T> createMapFromEnum(T[] constants) {
    HashMap<String, T> map = new HashMap<String, T>();
    for (T constant : constants) {
      map.put(constant.toString(), constant);
    }
    return map;
  }
  
  private final static String[] valuesOf(Enum<?>[] constants) {
    String[] out = new String[constants.length];
    for (int i = 0; i < constants.length; i++) {
      out[i] = constants[i].toString();
    }
    return out;
  }
  
  private final static int findEnumIndex(Enum<?>[] constants, Enum<?> e) {
    for (int i = 0; i < constants.length; i++) {
      if(e.equals(constants[i])) {
        return i;
      }        
    }
    throw new RuntimeException("The given enum does not exist in the given array!");
  }

  /**
   * Returns an object that is either an enum matching the Combo.getText() or the non-matching String.
   * @return The enum representing the text in the Combo if valid, otherwise the it returns the value if getText().
   */
  public Object getObject() {
    String text = mCombo.getText();
    Enum<?> e = mEnumMap.get(text);
    return e != null ? e : text;
  }


  public Combo getCombo() {
    return mCombo;
  }

}
