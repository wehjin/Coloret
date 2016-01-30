package com.rubyhuntersky.peregrine;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * @author wehjin
 * @since 1/29/16.
 */
public class AssetPrice implements Parcelable {
    public final String name;
    public final BigDecimal amount;

    public AssetPrice(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public AssetPrice(Parcel in) {
        this(in.readString(), (BigDecimal) in.readSerializable());
    }

    public AssetPrice() {
        this("-", BigDecimal.ONE);
    }

    public String getSharesString(BigDecimal amount) {
        final BigDecimal shareCount = amount.divide(this.amount, Values.SCALE, BigDecimal.ROUND_HALF_UP);
        final String shareCountString = shareCount.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString();
        return String.format("%s shares", shareCountString);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeSerializable(amount);
    }

    public static final Creator<AssetPrice> CREATOR = new Creator<AssetPrice>() {
        public AssetPrice createFromParcel(Parcel in) {
            return new AssetPrice(in);
        }

        public AssetPrice[] newArray(int size) {
            return new AssetPrice[size];
        }
    };
}