package pasa.cbentley.framework.core.ui.fx.wrapper;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import pasa.cbentley.framework.core.ui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.core.ui.fx.engine.CanvasHostFx;

public class JavaFxEventHandlerHide extends JavaFxEventHandlerAbstract implements EventHandler<WindowEvent> {

   public JavaFxEventHandlerHide(CoreUiFxCtx cuc, CanvasHostFx canvas) {
      super(cuc, canvas);
   }

   public void handle(WindowEvent event) {
      //#debug
      toDLog().pBridge("Hide", canvas, JavaFxEventHandlerHide.class, "handle", LVL_05_FINE, true);
   }

}
