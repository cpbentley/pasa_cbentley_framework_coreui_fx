package pasa.cbentley.framework.coreui.fx.engine;

import javafx.scene.input.KeyCode;
/**
 * Utility functions related to KeyCodes
 */
public class KeyCodeUtils {

   /** Compute a single suitable char summarizing the code, if any, and 0 otherwise. */
   public static char getSingleChar(KeyCode code) {
      switch (code) {
         case ENTER:
            return '\u21B5';
         case LEFT:
            return '\u2190';
         case UP:
            return '\u2191';
         case RIGHT:
            return '\u2192';
         case DOWN:
            return '\u2193';
         case COMMA:
            return ',';
         case MINUS:
            return '-';
         case PERIOD:
            return '.';
         case SLASH:
            return '/';
         case SEMICOLON:
            return ';';
         case EQUALS:
            return '=';
         case OPEN_BRACKET:
            return '[';
         case BACK_SLASH:
            return '\\';
         case CLOSE_BRACKET:
            return ']';
         case MULTIPLY:
            return '*';
         case ADD:
            return '+';
         case SUBTRACT:
            return '-';
         case DECIMAL:
            return '.';
         case DIVIDE:
            return '/';
         case BACK_QUOTE:
            return '`';
         case QUOTE:
            return '"';
         case AMPERSAND:
            return '&';
         case ASTERISK:
            return '*';
         case LESS:
            return '<';
         case GREATER:
            return '>';
         case BRACELEFT:
            return '{';
         case BRACERIGHT:
            return '}';
         case AT:
            return '@';
         case COLON:
            return ':';
         case CIRCUMFLEX:
            return '^';
         case DOLLAR:
            return '$';
         case EURO_SIGN:
            return '\u20AC';
         case EXCLAMATION_MARK:
            return '!';
         case LEFT_PARENTHESIS:
            return '(';
         case NUMBER_SIGN:
            return '#';
         case PLUS:
            return '+';
         case RIGHT_PARENTHESIS:
            return ')';
         case UNDERSCORE:
            return '_';
         case DIGIT0:
            return '0';
         case DIGIT1:
            return '1';
         case DIGIT2:
            return '2';
         case DIGIT3:
            return '3';
         case DIGIT4:
            return '4';
         case DIGIT5:
            return '5';
         case DIGIT6:
            return '6';
         case DIGIT7:
            return '7';
         case DIGIT8:
            return '8';
         case DIGIT9:
            return '9';
         default:
            break;
      }

      /*
      ** On Mac we display these unicode symbols,
      ** otherwise we default to the Text version of the char.
      */
      if (com.sun.javafx.PlatformUtil.isMac()) {
         switch (code) {
            case BACK_SPACE:
               return '\u232B';
            case ESCAPE:
               return '\u238B';
            case DELETE:
               return '\u2326';
            default:
               break;
         }
      }
      return 0;
   }
}