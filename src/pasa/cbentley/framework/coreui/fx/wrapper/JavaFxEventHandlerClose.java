package pasa.cbentley.framework.coreui.fx.wrapper;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import pasa.cbentley.framework.coreui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.coreui.fx.engine.CanvasHostFx;
import pasa.cbentley.framework.coreui.src4.event.CanvasHostEvent;
import pasa.cbentley.framework.coreui.src4.interfaces.ITechEventHost;

public class JavaFxEventHandlerClose extends JavaFxEventHandlerAbstract implements EventHandler<WindowEvent> {

   public JavaFxEventHandlerClose(CoreUiFxCtx cuc, CanvasHostFx canvas) {
      super(cuc, canvas);
   }

   public void handle(WindowEvent event) {
      //#debug
      toDLog().pBridge("Close", canvas, JavaFxEventHandlerClose.class, "handle", LVL_05_FINE, true);

      CanvasHostEvent ge = new CanvasHostEvent(cuc, ITechEventHost.ACTION_1_CLOSE, canvas);
      canvas.eventBridge(ge);
   }

}
