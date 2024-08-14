package pasa.cbentley.framework.core.ui.fx.engine;

import pasa.cbentley.framework.core.ui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.core.ui.j2se.engine.HostServiceUiJ2se;

public class HostServiceUiFx extends HostServiceUiJ2se {

   public HostServiceUiFx(CoreUiFxCtx cuc) {
      super(cuc);
   }

   public boolean isHostServiceActive(int serviceID) {
      switch (serviceID) {
         default:
            return super.isHostServiceActive(serviceID);
      }
   }

   public boolean isHostServiceSupported(int serviceID) {
      switch (serviceID) {
         default:
            return isHostServiceSupported(serviceID);
      }
   }

   public boolean setHostServiceActive(int serviceID, boolean isActive) {
      switch (serviceID) {
         default:
            return super.setHostServiceActive(serviceID, isActive);
      }
   }
}
