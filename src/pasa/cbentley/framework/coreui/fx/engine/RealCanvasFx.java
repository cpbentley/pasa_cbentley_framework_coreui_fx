package pasa.cbentley.framework.coreui.fx.engine;

import javafx.scene.canvas.Canvas;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;

public class RealCanvasFx extends Canvas implements IStringable {

   protected final CoreUiFxCtx cuc;

   public RealCanvasFx(CoreUiFxCtx cuc) {
      this.cuc = cuc;
   }
   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, RealCanvasFx.class, "@line5");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, RealCanvasFx.class);
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return cuc.getUC();
   }

   //#enddebug
   

}
