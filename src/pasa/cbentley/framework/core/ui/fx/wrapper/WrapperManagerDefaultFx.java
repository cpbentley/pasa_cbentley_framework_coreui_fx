package pasa.cbentley.framework.core.ui.fx.wrapper;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.framework.core.ui.fx.ctx.CoreUiFxCtx;
import pasa.cbentley.framework.core.ui.j2se.wrapper.WrapperManagerDefaultJ2se;
import pasa.cbentley.framework.core.ui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.core.ui.src4.interfaces.IWrapperManager;
import pasa.cbentley.framework.core.ui.src4.tech.IBOCanvasHost;
import pasa.cbentley.framework.core.ui.src4.tech.ITechWrapper;
import pasa.cbentley.framework.core.ui.src4.wrapper.WrapperAbstract;

/**
 * Creates 
 * @author Charles Bentley
 *
 */
public class WrapperManagerDefaultFx extends WrapperManagerDefaultJ2se implements IWrapperManager {

   protected final CoreUiFxCtx cuic;

   public WrapperManagerDefaultFx(CoreUiFxCtx cuc) {
      super(cuc);
      this.cuic = cuc;
   }

   public WrapperAbstractFx createNewWrapper(ByteObject tech) {
      return new WrapperFxTopStage(cuic);
   }


   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, WrapperManagerDefaultFx.class, 44);
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, WrapperManagerDefaultFx.class, 44);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
      
   }
   //#enddebug
   


}
