package pasa.cbentley.framework.core.ui.fx.ctx;

import javafx.stage.Stage;
import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.fx.ctx.FxCoreCtx;
import pasa.cbentley.core.src4.stator.IStatorFactory;
import pasa.cbentley.framework.core.draw.fx.ctx.CoreDrawFxCtx;
import pasa.cbentley.framework.core.io.src5.ctx.CoreIO5Ctx;
import pasa.cbentley.framework.core.ui.fx.engine.CanvasFx;
import pasa.cbentley.framework.core.ui.fx.engine.HostDataUiFx;
import pasa.cbentley.framework.core.ui.fx.engine.HostFeatureUiFx;
import pasa.cbentley.framework.core.ui.fx.engine.HostServiceUiFx;
import pasa.cbentley.framework.core.ui.fx.engine.KeyMapFx;
import pasa.cbentley.framework.core.ui.fx.wrapper.CanvasOwnerDefaultFx;
import pasa.cbentley.framework.core.ui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.core.ui.j2se.engine.HostDataUiJ2se;
import pasa.cbentley.framework.core.ui.j2se.engine.HostFeatureUiJ2se;
import pasa.cbentley.framework.core.ui.j2se.engine.HostServiceUiJ2se;
import pasa.cbentley.framework.core.ui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.core.ui.src4.engine.KeyMapAbstract;
import pasa.cbentley.framework.core.ui.src4.engine.WrapperAbstract;
import pasa.cbentley.framework.core.ui.src4.interfaces.IWrapperManager;
import pasa.cbentley.framework.core.ui.src4.tech.IBOCanvasHost;

public class CoreUiFxCtx extends CoreUiJ2seCtx {

   public static final int       CTX_ID = 486;

   protected final CoreDrawFxCtx cdc;

   protected final CoreIO5Ctx    cio5c;

   private HostDataUiFx          hostDataUiFx;

   private HostFeatureUiFx       hostFeatureFx;

   private HostServiceUiFx       hostServiceFx;

   protected final KeyMapFx      keyMap;

   private Stage                 stagePrimary;

   private StatorFactoryCoreUiFx statorFactory;

   /**
    * Core UI deals with file connections for drag and drop so we need a {@link CoreIO5Ctx}
    * @param cdc
    * @param cio5c
    * @param sc
    */
   public CoreUiFxCtx(IConfigCoreUIFx config, CoreDrawFxCtx cdc, CoreIO5Ctx cio5c) {
      super(config == null ? new ConfigCoreUIFxDefault(cdc.getUC()) : config, cdc);
      this.cdc = cdc;
      this.cio5c = cio5c;
      keyMap = new KeyMapFx(uc);

      hostDataUiFx = new HostDataUiFx(this);
      hostFeatureFx = new HostFeatureUiFx(this);
      hostServiceFx = new HostServiceUiFx(this);

      if (this.getClass() == CoreUiFxCtx.class) {
         a_Init();
      }

      //#debug
      toDLog().pInit("", this, CoreUiFxCtx.class, "Created@71", LVL_04_FINER, true);

   }

   protected void applySettings(ByteObject settingsNew, ByteObject settingsOld) {

   }

   /**
    * {@link IBOCanvasHost}
    */
   public CanvasHostAbstract createCanvasHost(WrapperAbstract wrapper, ByteObject canvasTech) {
      CanvasFx canvasHost = new CanvasFx(this, canvasTech);

      return canvasHost;
   }

   public IWrapperManager createCanvasOwnerDefault() {
      return new CanvasOwnerDefaultFx(this);
   }

   public CoreDrawFxCtx getCoreDrawFxCtx() {
      return cdc;
   }

   public CoreIO5Ctx getCoreIO5Ctx() {
      return cio5c;
   }

   public int getCtxID() {
      return CTX_ID;
   }

   public FxCoreCtx getFCC() {
      return cdc.getFC();
   }

   public HostDataUiJ2se getHostDataUIJ2se() {
      return hostDataUiFx;
   }

   public HostFeatureUiJ2se getHostFeatureUiJ2se() {
      return hostFeatureFx;
   }

   public HostServiceUiJ2se getHostServiceUiJ2se() {
      return hostServiceFx;
   }

   public KeyMapAbstract getKeyMap() {
      return keyMap;
   }

   public KeyMapFx getKeyMapFx() {
      return keyMap;
   }

   public Stage getStagePrimary() {
      return stagePrimary;
   }

   public IStatorFactory getStatorFactory() {
      if (statorFactory == null) {
         statorFactory = new StatorFactoryCoreUiFx(this);
      }
      return statorFactory;
   }


   public void setPrimaryState(Stage stage) {
      stagePrimary = stage;
   }

}
