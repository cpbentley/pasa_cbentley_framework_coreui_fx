package pasa.cbentley.framework.coreui.fx.engine;

import javafx.scene.input.KeyCode;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.framework.coreui.src4.engine.KeyMapAbstract;
import pasa.cbentley.framework.coreui.src4.tech.ITechCodes;

public class KeyMapFx extends KeyMapAbstract {

   public KeyMapFx(UCtx uc) {
      super(uc);
      // TODO Auto-generated constructor stub
   }

   public int getKeyMappedToFramework(KeyCode key) {
      //up and down
      if (key == KeyCode.DOWN) {
         return ITechCodes.KEY_DOWN;
      } else if (key == KeyCode.UP) {
         return ITechCodes.KEY_UP;
      }
      //left and right
      else if (key == KeyCode.LEFT) {
         return ITechCodes.KEY_LEFT;
      } else if (key == KeyCode.RIGHT) {
         return ITechCodes.KEY_RIGHT;
      }
      //
      else if (key == KeyCode.END) {
         return ITechCodes.KEY_CANCEL;
      }
      //back space for canek
      else if (key == KeyCode.BACK_SPACE) {
         return ITechCodes.KEY_DELETE_CHAR;
      }
      ///
      else if (key == KeyCode.HOME) {
         return ITechCodes.KEY_BACK;
      }
      //enter key
      else if (key == KeyCode.ENTER) {
         return ITechCodes.KEY_FIRE;
      }
      // dot key for the photo
      else if (key == KeyCode.DECIMAL) {
         return ITechCodes.KEY_PHOTO;
      } else if (key == KeyCode.NUMPAD0) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM0 : ITechCodes.KEY_NUM0;
      } else if (key == KeyCode.NUMPAD1) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM7 : ITechCodes.KEY_NUM1;
      } else if (key == KeyCode.NUMPAD2) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM8 : ITechCodes.KEY_NUM2;
      } else if (key == KeyCode.NUMPAD3) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM9 : ITechCodes.KEY_NUM3;
      } else if (key == KeyCode.NUMPAD4) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM7 : ITechCodes.KEY_NUM4;
      } else if (key == KeyCode.NUMPAD5) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM5 : ITechCodes.KEY_NUM5;
      } else if (key == KeyCode.NUMPAD6) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM1 : ITechCodes.KEY_NUM6;
      } else if (key == KeyCode.NUMPAD7) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM7 : ITechCodes.KEY_NUM7;
      } else if (key == KeyCode.NUMPAD8) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM2 : ITechCodes.KEY_NUM8;
      } else if (key == KeyCode.NUMPAD9) {
         return (isNumPadInvert()) ? ITechCodes.KEY_NUM3 : ITechCodes.KEY_NUM9;
      }
      //star and pounds
      else if (key == KeyCode.MULTIPLY) {
         return ITechCodes.KEY_STAR;
      } else if (key == KeyCode.DIVIDE) {
         return ITechCodes.KEY_POUND;
      }
      // plus and mins KeyCode.MINUS
      else if (key.impl_getCode() == 109) {
         return ITechCodes.KEY_MINUS;
      } else if (key.impl_getCode() == 107) { //KeyCode.PLUS
         return ITechCodes.KEY_PLUS;
      }
      // menu left and right
      else if (key == KeyCode.PAGE_UP) {
         return ITechCodes.KEY_MENU_LEFT;
      } else if (key == KeyCode.PAGE_DOWN) {
         return ITechCodes.KEY_MENU_RIGHT;
      }
      //function keys
      else if (key == KeyCode.F1) {
         return ITechCodes.KEY_F1;
      } else if (key == KeyCode.F2) {
         return ITechCodes.KEY_F2;
      } else if (key == KeyCode.F3) {
         return ITechCodes.KEY_F3;
      } else if (key == KeyCode.F4) {
         return ITechCodes.KEY_F4;
      } else if (key == KeyCode.F5) {
         return ITechCodes.KEY_F5;
      } else if (key == KeyCode.F6) {
         return ITechCodes.KEY_F6;
      } else if (key == KeyCode.F7) {
         return ITechCodes.KEY_F7;
      } else if (key == KeyCode.F8) {
         return ITechCodes.KEY_F8;
      } else if (key == KeyCode.F9) {
         return ITechCodes.KEY_F9;
      } else if (key == KeyCode.F10) {
         return ITechCodes.KEY_F10;
      } else if (key == KeyCode.F11) {
         return ITechCodes.KEY_F11;
      } else if (key == KeyCode.F12) {
         return ITechCodes.KEY_F12;
      }
      //escape
      else if (key == KeyCode.ESCAPE) {
         return ITechCodes.KEY_ESCAPE;
      } else if (key == KeyCode.CONTROL) {
         return ITechCodes.KEY_CTRL;
      } else if (key == KeyCode.ALT) {
         return ITechCodes.KEY_ALT;
      } else if (key == KeyCode.SHIFT) {
         return ITechCodes.KEY_SHIFT;
      } else if (key == KeyCode.CAPS) {
         return ITechCodes.KEY_CAPS_LOCK;
      } else if (key == KeyCode.TAB) {
         return ITechCodes.KEY_TAB;
      } else {
         return key.impl_getCode();
      }
   }

   public int getKeyMappedToFramework(int key) {
      switch (key) {
         case 0x73:
            return ITechCodes.KEY_F3;
         default:
            throw new IllegalArgumentException("" + key);
      }
   }

}
