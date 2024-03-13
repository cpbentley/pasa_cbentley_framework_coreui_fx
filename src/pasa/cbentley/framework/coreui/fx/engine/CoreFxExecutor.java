package pasa.cbentley.framework.coreui.fx.engine;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.j2se.engine.J2SEThreader;

public class CoreFxExecutor extends J2SEThreader {

   protected final CoreUiFxCtx cuc;

   public CoreFxExecutor(CoreUiFxCtx cuc) {
      super(cuc);
      this.cuc = cuc;
   }

   public void executeWorker(Runnable run) {
   }

   public void executeMainNow(Runnable run) {
   }

   public void executeMainLater(Runnable run) {
      cuc.runGUI(run);
   }

   public void callSerially(Runnable run) {
      cuc.runGUI(run);
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "CoreSwingExecutor");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "CoreSwingExecutor");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return cuc.getUC();
   }

   //#enddebug

}
