package pasa.cbentley.framework.core.ui.fx.engine;

import pasa.cbentley.core.src4.interfaces.IHostData;
import pasa.cbentley.framework.core.ui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.core.ui.j2se.engine.HostDataUiJ2se;
import pasa.cbentley.framework.core.ui.src4.tech.ITechHostDataDrawUi;

/**
 * This class deals with all HostData, from Draw, UI and Core.
 * 
 * If you want a class limited to Draw, you 
 * @author Charles Bentley
 *
 */
public class HostDataUiFx extends HostDataUiJ2se implements IHostData, ITechHostDataDrawUi {


   public HostDataUiFx(CoreUiFxCtx cuc) {
      super(cuc);
   }

   public float getHostDataFloat(int dataID) {
      switch (dataID) {

         default:
            break;
      }
      return super.getHostDataFloat(dataID);
   }

   public int getHostDataInt(int dataID) {
      switch (dataID) {
         default:
            return super.getHostDataInt(dataID);
      }
   }

   public Object getHostDataObject(int dataID) {
      switch (dataID) {
         default:
            return super.getHostDataObject(dataID);
      }
   }

   public String getHostDataString(int dataID) {
      switch (dataID) {
         default:
            return super.getHostDataString(dataID);
      }
   }

}
