package br.com.cis.sdk;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 *
 * @author Samuelson Brito
 */
public interface ICisBioxSDK extends Library {

    ICisBioxSDK INSTANCE = (ICisBioxSDK) Native.loadLibrary("CIS_SDK", ICisBioxSDK.class);

    public int CIS_SDK_Biometrico_Iniciar();

    public int CIS_SDK_Biometrico_Finalizar();

    public int CIS_SDK_Biometrico_LerDigital(PointerByReference pTemplate);

    public Pointer CIS_SDK_Biometrico_LerDigital_RetornoPonteiro(IntByReference iRetorno);

    public int CIS_SDK_Biometrico_CancelarLeitura();

    public int CIS_SDK_Biometrico_CompararDigital(PointerByReference pAmostra1, PointerByReference pAmostra2);

    public Pointer CIS_SDK_Biometrico_LerDigital_LerWSQ(IntByReference iRetorno, IntByReference iSize);

    public Pointer CIS_SDK_Versao();

}
