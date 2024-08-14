package pasa.cbentley.framework.core.ui.fx.wrapper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.framework.core.ui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.core.ui.fx.engine.CanvasHostFx;

public class JavaFxIconifiedChangeListener implements ChangeListener<Boolean>, IStringable {
   
   protected final CoreUiFxCtx cuc;
   protected final CanvasHostFx canvas;
   
   
   public JavaFxIconifiedChangeListener(CoreUiFxCtx cuc, CanvasHostFx canvas) {
      this.cuc = cuc;
      this.canvas = canvas;
      
   }
   public void changed(ObservableValue<? extends Boolean> arg0, Boolean old, Boolean newv) {
      //#debug
      toDLog().pBridge("Iconified " + newv, canvas, JavaFxIconifiedChangeListener.class, "changed", LVL_05_FINE, true);
   }
   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, JavaFxIconifiedChangeListener.class,38);
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, JavaFxIconifiedChangeListener.class);
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return cuc.getUC();
   }
   //#enddebug
   


}
