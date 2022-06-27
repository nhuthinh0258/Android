package com.example.baitaplon.model;

public class Taikhoan {
    private int mID;
    private String mTentaikhoan;
    private String mMatkhau;
    private String mEmail;
    private int mPhanquyen;

    public Taikhoan(String mTentaikhoan, String mMatkhau, String mEmail, int mPhanquyen) {
        this.mTentaikhoan = mTentaikhoan;
        this.mMatkhau = mMatkhau;
        this.mEmail = mEmail;
        this.mPhanquyen = mPhanquyen;
    }

    public Taikhoan(String mTentaikhoan, String mEmail) {
        this.mTentaikhoan = mTentaikhoan;
        this.mEmail = mEmail;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmTentaikhoan() {
        return mTentaikhoan;
    }

    public void setmTentaikhoan(String mTentaikhoan) {
        this.mTentaikhoan = mTentaikhoan;
    }

    public String getmMatkhau() {
        return mMatkhau;
    }

    public void setmMatkhau(String mMatkhau) {
        this.mMatkhau = mMatkhau;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmPhanquyen() {
        return mPhanquyen;
    }

    public void setmPhanquyen(int mPhanquyen) {
        this.mPhanquyen = mPhanquyen;
    }
}
