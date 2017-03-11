/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cis.sdk;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 *
 * @author Samuelson Brito
 */
public class CisBiox extends CisBioxSDK{
    
    public int iniciar() {
        int iRetorno = this.CIS_SDK_Biometrico_Iniciar();
        return iRetorno;
    }

    public int finalizar() {
        int iRetorno = this.CIS_SDK_Biometrico_Finalizar();
        return iRetorno;
    }

    public int lerDigital(PointerByReference pTemplate) {
        int iRetorno = this.CIS_SDK_Biometrico_LerDigital(pTemplate);
        return iRetorno;
    }

    public Pointer lerDigital_RetornoPonteiro(IntByReference iRetorno) {
        Pointer pDigital;
        pDigital = this.CIS_SDK_Biometrico_LerDigital_RetornoPonteiro(iRetorno);
        return pDigital;
    }

    public int cancelarLeitura() {
        int iRetorno = this.CIS_SDK_Biometrico_CancelarLeitura();
        return iRetorno;
    }

    public int compararDigital(PointerByReference pAmostra1, PointerByReference pAmostra2) {
        int iRetorno = this.CIS_SDK_Biometrico_CompararDigital(pAmostra1, pAmostra2);
        return iRetorno;
    }

    public Pointer lerDigital_LerWSQ(IntByReference iRetorno, IntByReference iSize) {
        Pointer pImagem;
        pImagem = this.CIS_SDK_Biometrico_LerDigital_LerWSQ(iRetorno, iSize);
        return pImagem;
    }

    public Pointer versao() {
        Pointer p;
        p = this.CIS_SDK_Versao();
        return p;
    }
    
}
