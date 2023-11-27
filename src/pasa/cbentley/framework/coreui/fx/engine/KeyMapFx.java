package pasa.cbentley.framework.coreui.fx.engine;

import javafx.scene.input.KeyCode;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.framework.coreui.src4.engine.KeyMapAbstract;
import pasa.cbentley.framework.coreui.src4.tech.IBCodes;

public class KeyMapFx extends KeyMapAbstract {

   public KeyMapFx(UCtx uc) {
      super(uc);
      // TODO Auto-generated constructor stub
   }

   public int getKeyMappedToFramework(KeyCode key) {
      //up and down
      if (key == KeyCode.DOWN) {
         return IBCodes.KEY_DOWN;
      } else if (key == KeyCode.UP) {
         return IBCodes.KEY_UP;
      }
      //left and right
      else if (key == KeyCode.LEFT) {
         return IBCodes.KEY_LEFT;
      } else if (key == KeyCode.RIGHT) {
         return IBCodes.KEY_RIGHT;
      }
      //
      else if (key == KeyCode.END) {
         return IBCodes.KEY_CANCEL;
      }
      //back space for canek
      else if (key == KeyCode.BACK_SPACE) {
         return IBCodes.KEY_DELETE_CHAR;
      }
      ///
      else if (key == KeyCode.HOME) {
         return IBCodes.KEY_BACK;
      }
      //enter key
      else if (key == KeyCode.ENTER) {
         return IBCodes.KEY_FIRE;
      }
      // dot key for the photo
      else if (key == KeyCode.DECIMAL) {
         return IBCodes.KEY_PHOTO;
      } else if (key == KeyCode.NUMPAD0) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM0 : IBCodes.KEY_NUM0;
      } else if (key == KeyCode.NUMPAD1) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM7 : IBCodes.KEY_NUM1;
      } else if (key == KeyCode.NUMPAD2) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM8 : IBCodes.KEY_NUM2;
      } else if (key == KeyCode.NUMPAD3) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM9 : IBCodes.KEY_NUM3;
      } else if (key == KeyCode.NUMPAD4) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM7 : IBCodes.KEY_NUM4;
      } else if (key == KeyCode.NUMPAD5) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM5 : IBCodes.KEY_NUM5;
      } else if (key == KeyCode.NUMPAD6) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM1 : IBCodes.KEY_NUM6;
      } else if (key == KeyCode.NUMPAD7) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM7 : IBCodes.KEY_NUM7;
      } else if (key == KeyCode.NUMPAD8) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM2 : IBCodes.KEY_NUM8;
      } else if (key == KeyCode.NUMPAD9) {
         return (isNumPadInvert()) ? IBCodes.KEY_NUM3 : IBCodes.KEY_NUM9;
      }
      //star and pounds
      else if (key == KeyCode.MULTIPLY) {
         return IBCodes.KEY_STAR;
      } else if (key == KeyCode.DIVIDE) {
         return IBCodes.KEY_POUND;
      }
      // plus and mins KeyCode.MINUS
      else if (key.impl_getCode() == 109) {
         return IBCodes.KEY_MINUS;
      } else if (key.impl_getCode() == 107) { //KeyCode.PLUS
         return IBCodes.KEY_PLUS;
      }
      // menu left and right
      else if (key == KeyCode.PAGE_UP) {
         return IBCodes.KEY_MENU_LEFT;
      } else if (key == KeyCode.PAGE_DOWN) {
         return IBCodes.KEY_MENU_RIGHT;
      }
      //function keys
      else if (key == KeyCode.F1) {
         return IBCodes.KEY_F1;
      } else if (key == KeyCode.F2) {
         return IBCodes.KEY_F2;
      } else if (key == KeyCode.F3) {
         return IBCodes.KEY_F3;
      } else if (key == KeyCode.F4) {
         return IBCodes.KEY_F4;
      } else if (key == KeyCode.F5) {
         return IBCodes.KEY_F5;
      } else if (key == KeyCode.F6) {
         return IBCodes.KEY_F6;
      } else if (key == KeyCode.F7) {
         return IBCodes.KEY_F7;
      } else if (key == KeyCode.F8) {
         return IBCodes.KEY_F8;
      } else if (key == KeyCode.F9) {
         return IBCodes.KEY_F9;
      } else if (key == KeyCode.F10) {
         return IBCodes.KEY_F10;
      } else if (key == KeyCode.F11) {
         return IBCodes.KEY_F11;
      } else if (key == KeyCode.F12) {
         return IBCodes.KEY_F12;
      }
      //escape
      else if (key == KeyCode.ESCAPE) {
         return IBCodes.KEY_ESCAPE;
      } else if (key == KeyCode.CONTROL) {
         return IBCodes.KEY_CTRL;
      } else if (key == KeyCode.ALT) {
         return IBCodes.KEY_ALT;
      } else if (key == KeyCode.SHIFT) {
         return IBCodes.KEY_SHIFT;
      } else if (key == KeyCode.CAPS) {
         return IBCodes.KEY_CAPS_LOCK;
      } else if (key == KeyCode.TAB) {
         return IBCodes.KEY_TAB;
      } else {
         return key.impl_getCode();
      }
   }

   public int getKeyMappedToFramework(int key) {
      switch (key) {
         case 0x73:
            return IBCodes.KEY_F3;
         default:
            throw new IllegalArgumentException("" + key);
      }
   }

}
