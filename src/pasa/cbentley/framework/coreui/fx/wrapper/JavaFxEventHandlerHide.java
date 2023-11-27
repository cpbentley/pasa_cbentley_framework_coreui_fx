package pasa.cbentley.framework.coreui.fx.wrapper;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.fx.engine.CanvasHostFx;
import pasa.cbentley.framework.coreui.src4.event.CanvasHostEvent;
import pasa.cbentley.framework.coreui.src4.interfaces.ITechEventHost;

public class JavaFxEventHandlerHide extends JavaFxEventHandlerAbstract implements EventHandler<WindowEvent> {

   public JavaFxEventHandlerHide(CoreUiFxCtx cuc, CanvasHostFx canvas) {
      super(cuc, canvas);
   }

   public void handle(WindowEvent event) {
      //#debug
      toDLog().pBridge("Hide", canvas, JavaFxEventHandlerHide.class, "handle", LVL_05_FINE, true);
   }

}
