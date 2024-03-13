package pasa.cbentley.framework.coreui.fx.wrapper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.fx.engine.CanvasHostFx;

public class JavaFxFocusChangeListener implements ChangeListener<Boolean>, IStringable {
   
   protected final CoreUiFxCtx cuc;
   protected final CanvasHostFx canvas;
   
   
   public JavaFxFocusChangeListener(CoreUiFxCtx cuc, CanvasHostFx canvas) {
      this.cuc = cuc;
      this.canvas = canvas;
      
   }
   public void changed(ObservableValue<? extends Boolean> arg0, Boolean old, Boolean newv) {
      //this is the one. canvas does not get focus
      cuc.toDLog().pBridge("Focus " + newv, canvas, JavaFxFocusChangeListener.class,"changed", LVL_04_FINER, true);
      if (newv) {
         canvas.focusGainedBridge();
      } else {
         canvas.focusLostBridge();
      }
   }
   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, JavaFxFocusChangeListener.class);
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, JavaFxFocusChangeListener.class);
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return cuc.getUC();
   }
   //#enddebug
   


}
