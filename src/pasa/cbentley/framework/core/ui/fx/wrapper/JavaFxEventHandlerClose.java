package pasa.cbentley.framework.core.ui.fx.wrapper;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import pasa.cbentley.framework.core.ui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.core.ui.fx.engine.CanvasHostFx;
import pasa.cbentley.framework.core.ui.src4.event.CanvasHostEvent;
import pasa.cbentley.framework.core.ui.src4.interfaces.ITechEventHost;

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
