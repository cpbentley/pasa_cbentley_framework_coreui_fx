package pasa.cbentley.framework.coreui.fx.ctx;

import javafx.application.Platform;
import javafx.stage.Stage;
import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.interfaces.IExecutor;
import pasa.cbentley.framework.coredraw.fx.ctx.CoreDrawFxCtx;
import pasa.cbentley.framework.coreio.src5.ctx.CoreIO5Ctx;
import pasa.cbentley.framework.coreui.fx.engine.CanvasFx;
import pasa.cbentley.framework.coreui.fx.engine.CoreFxExecutor;
import pasa.cbentley.framework.coreui.fx.engine.HostUIFx;
import pasa.cbentley.framework.coreui.fx.engine.KeyMapFx;
import pasa.cbentley.framework.coreui.fx.wrapper.CanvasOwnerDefaultFx;
import pasa.cbentley.framework.coreui.j2se.ctx.CoreUiJ2seCtx;
import pasa.cbentley.framework.coreui.src4.engine.CanvasHostAbstract;
import pasa.cbentley.framework.coreui.src4.engine.KeyMapAbstract;
import pasa.cbentley.framework.coreui.src4.engine.WrapperAbstract;
import pasa.cbentley.framework.coreui.src4.interfaces.IWrapperManager;
import pasa.cbentley.framework.coreui.src4.interfaces.IHostUI;
import pasa.cbentley.framework.coreui.src4.tech.IBOCanvasHost;

public class CoreUiFxCtx extends CoreUiJ2seCtx {

   public static final int       CTX_ID = 486;

   protected final CoreDrawFxCtx cdc;

   protected final CoreIO5Ctx    cio5c;

   private CoreFxExecutor        executor;

   protected final KeyMapFx      keyMap;

   private Stage                 stagePrimary;

   private HostUIFx              hostui;

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
      executor = new CoreFxExecutor(this);
      keyMap = new KeyMapFx(uc);
      hostui = new HostUIFx(this);

      if (this.getClass() == CoreUiFxCtx.class) {
         a_Init();
      }
   }

   protected void applySettings(ByteObject settingsNew, ByteObject settingsOld) {

   }

   public int getCtxID() {
      return CTX_ID;
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

   public Stage getStagePrimary() {
      return stagePrimary;
   }

   public IExecutor getExecutor() {
      return executor;
   }

   public int getHostInt(int id) {
      return super.getHostInt(id);
   }

   public KeyMapAbstract getKeyMap() {
      return keyMap;
   }

   public KeyMapFx getKeyMapFx() {
      return keyMap;
   }

   public void runGUI(Runnable run) {
      cdc.callSerially(run);
   }

   public void setPrimaryState(Stage stage) {
      stagePrimary = stage;
   }

   public IHostUI getHostUI() {
      return hostui;
   }

}
