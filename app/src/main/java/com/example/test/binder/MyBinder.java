package com.example.test.binder;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileDescriptor;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/7/12
 * describe:
 **/
public class MyBinder implements IBinder {

    @Nullable
    @Override
    public String getInterfaceDescriptor() throws RemoteException {
        return null;
    }

    @Override
    public boolean pingBinder() {
        return false;
    }

    @Override
    public boolean isBinderAlive() {
        return false;
    }

    @Nullable
    @Override
    public IInterface queryLocalInterface(@NonNull String s) {
        return null;
    }

    @Override
    public void dump(@NonNull FileDescriptor fileDescriptor, @Nullable String[] strings) throws RemoteException {

    }

    @Override
    public void dumpAsync(@NonNull FileDescriptor fileDescriptor, @Nullable String[] strings) throws RemoteException {

    }

    @Override
    public boolean transact(int i, @NonNull Parcel parcel, @Nullable Parcel parcel1, int i1) throws RemoteException {
        return false;
    }

    @Override
    public void linkToDeath(@NonNull DeathRecipient deathRecipient, int i) throws RemoteException {

    }

    @Override
    public boolean unlinkToDeath(@NonNull DeathRecipient deathRecipient, int i) {
        return false;
    }
}
