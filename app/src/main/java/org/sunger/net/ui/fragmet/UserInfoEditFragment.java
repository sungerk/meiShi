package org.sunger.net.ui.fragmet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.provider.MediaStore;
import android.view.View;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.soundcloud.android.crop.Crop;

import org.sunger.net.app.App;
import org.sunger.net.entity.OauthUserEntity;
import org.sunger.net.entity.UserEntity;
import org.sunger.net.presenter.EditUserInfoPresenter;
import org.sunger.net.presenter.impl.EditUserInfoPresenterImpl;
import org.sunger.net.ui.widget.MProgressDialog;
import org.sunger.net.ui.widget.RadioGroupPreference;
import org.sunger.net.utils.DrawableUtils;
import org.sunger.net.utils.FileUtils;
import org.sunger.net.utils.GlideUtils;
import org.sunger.net.utils.SdcardUtils;
import org.sunger.net.utils.TimeUtils;
import org.sunger.net.utils.UriUtils;
import org.sunger.net.view.EditUserInfoView;

import java.io.File;

import sunger.org.demo.R;

/**
 * Created by sunger on 2015/11/27.
 */
public class UserInfoEditFragment extends BasePreferenceFragment implements OnPreferenceClickListener, EditUserInfoView {
    private static final String IMAGEPATH = SdcardUtils.getSdcardPath() + "meiPai/Image/avatar/";
    private static final String IMAGNAME = IMAGEPATH + "camera_avatar.jpg";
    private static final int CAMERA_REQUEST_CODE = 8080;
    private Preference mPreferenceUserAvatar;
    private EditTextPreference mPreferenceScreenName;
    private RadioGroupPreference mPreferenceUserGender;
    private EditTextPreference mPreferenceUserDescription;
    private EditUserInfoPresenter mPresenter;
    private MProgressDialog mProgressDialog;
    private String newUserAvatarPath = null;


    public static UserInfoEditFragment newInatance() {
        return new UserInfoEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_user_info_edit);
        mPresenter = new EditUserInfoPresenterImpl(this);
    }

    @TargetApi(23)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialog = new MProgressDialog(getActivity());
        mProgressDialog.setTitle(R.string.msg_now_update);
        mPreferenceUserAvatar = findPreference(getString(R.string.preference_user_avatar));
        mPreferenceScreenName = findPreference(getString(R.string.preference_screen_name));
        mPreferenceUserGender = findPreference(getString(R.string.preference_user_gender));
        mPreferenceUserDescription = findPreference(getString(R.string.preference_user_description));
        OauthUserEntity oauthUserEntity = App.getInstance().getOauthUserEntity();
        UserEntity entity = oauthUserEntity.getUser();
        entity.getDescription();
        mPreferenceScreenName.setSummary(entity.getScreen_name());
        String gender = entity.getGender().contains("m") ? getString(R.string.gender_male) : getString(R.string.gender_female);
        mPreferenceUserGender.setSummary(gender);
        mPreferenceUserDescription.setSummary(entity.getDescription());
        mPreferenceUserAvatar.setOnPreferenceClickListener(this);
        mPreferenceScreenName.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                mPreferenceScreenName.setSummary(newValue.toString());
                return false;
            }
        });
        mPreferenceUserGender.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                mPreferenceUserGender.setSummary(newValue.toString());
                return false;
            }
        });
        mPreferenceUserDescription.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                mPreferenceUserDescription.setSummary(newValue.toString());
                return false;
            }
        });

        BitmapTypeRequest builder = Glide.with(this).load(entity.getAvatar()).asBitmap();
        GlideUtils.loadCircleImage(builder, mPreferenceUserAvatar);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String items[] = getResources().getStringArray(R.array.selectImage);
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity()).setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Crop.pickImage(getActivity());
                        break;
                    case 1:
                        FileUtils.deleteFile(IMAGNAME);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Uri outputFileUri = Uri.fromFile(FileUtils.createFile(IMAGNAME));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        getActivity().startActivityForResult(intent, CAMERA_REQUEST_CODE);
                        break;
                    case 2:
                        dialog.cancel();
                        break;
                }
            }
        });
        dialog.show();
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == CAMERA_REQUEST_CODE) {
            beginCrop(Uri.fromFile(new File(IMAGNAME)));
        } else if (requestCode == Crop.REQUEST_PICK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(result);
        }
    }

    private void handleCrop(Intent result) {
        Uri uri = Crop.getOutput(result);
        newUserAvatarPath = UriUtils.getPath(getActivity(), uri);
        Bitmap avatorBitmap = BitmapFactory.decodeFile(newUserAvatarPath);
        Drawable drawable = DrawableUtils.roundedBitmap(avatorBitmap);
        mPreferenceUserAvatar.setIcon(drawable);
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(FileUtils.createFile(IMAGEPATH + TimeUtils.getCurrentTime() + ".png"));
        Crop.of(source, destination).asSquare().start(getActivity());
    }

    public void save() {
        mProgressDialog.show();
        String gender = mPreferenceUserGender.getSummary().toString().contains(getString(R.string.gender_male)) ? "m" : "f";
        mPresenter.update(mPreferenceScreenName.getSummary().toString(), gender, mPreferenceUserDescription.getSummary().toString(), newUserAvatarPath);
    }

    @Override
    public void showError(String msg) {
        mProgressDialog.setTitle(msg);
        mProgressDialog.delayedDismiss();
    }

    @Override
    public void updateSuccess() {
        mProgressDialog.setTitle(R.string.msg_update_success);
        mProgressDialog.delayedDismiss();
    }
}
