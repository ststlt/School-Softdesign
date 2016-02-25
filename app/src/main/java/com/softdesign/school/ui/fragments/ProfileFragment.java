package com.softdesign.school.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.preferences.UserPreferenses;
import com.softdesign.school.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    private static final String FUNCTIONALITY_PROFILE_VIEW = "prifile_view";
    private static final String FUNCTIONALITY_PROFILE_EDIT = "prifile_edit";
    private static String sCurrentFunctionality = FUNCTIONALITY_PROFILE_VIEW;

    UserPreferenses userFields;
    List<String> mUserProfileData;
    View mainView = null;

    @Bind({R.id.txt_phone_number, R.id.txt_email_url, R.id.txt_vk_url, R.id.txt_github_url})
    List<TextView> txtViewsValues;
    @Bind({R.id.txt_phone_label, R.id.txt_email_label, R.id.txt_vk_label, R.id.txt_github_label})
    List<TextView> txtViewsLabels;
    @Bind({R.id.et_phone_wrapper, R.id.et_email_wrapper, R.id.et_vk_wrapper, R.id.et_github_wrapper})
    List<TextInputLayout> etViewsWrappers;
    @Bind({R.id.et_phone_number, R.id.et_email_url, R.id.et_vk_url, R.id.et_github_url})
    List<EditText> etViewsValues;



    public ProfileFragment() {
        this.setRetainInstance(true); // пересоздавать фрагмент при повороте экрана
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userFields = new UserPreferenses();
        mUserProfileData = userFields.loadUserProfileData(); // получаем данные из локальной модели

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*if (mainView == null) {
            // Если представления нет, надуваем его
            View mainView = inflater.inflate(R.layout.fragment_profile, container, false);
            ButterKnife.bind(this, mainView);
        }*/

        /* Надуваем из xml лэйаута view элемент */
//        View convertView = inflater.inflate(R.layout.fragment_profile, null, false);
        mainView = inflater.inflate(R.layout.fragment_profile, null, false);
        ButterKnife.bind(this, mainView);

        /* Задаем в тулбаре выбранное в меню навигации наименование */
//        getActivity().setTitle(getResources().getString(R.string.drawer_profile));

        getActivity().setTitle(getResources().getString(R.string.fragment_profile_title));
        ((MainActivity) getActivity()).lockAppBar(false);

        setFieldsData(txtViewsValues, mUserProfileData); // заполняем View элементы данными
        setFieldsData(etViewsValues, mUserProfileData);
        setupFunctionality(sCurrentFunctionality); // выставялем текущую функциональность

        return mainView;
//        return convertView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab); //инициализируем fab из активити
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams(); // получаем параметры Layout fab приведенные к родителю
        params.setAnchorId(R.id.appbar_layout); //выставляем привязку якоря к appBarLayout
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT; //выставляем anchorGravity
        fab.setLayoutParams(params);
        fab.setImageResource(R.drawable.ic_phone_24dp); // меняем иконку fab

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //создаем и вешаем новый обработчик на fab
                if (sCurrentFunctionality.equals(FUNCTIONALITY_PROFILE_VIEW)) {  //выбираем действие для fab в зависимости от текущего режима
                    setupFunctionality(FUNCTIONALITY_PROFILE_EDIT);
                } else {
                    setupFunctionality(FUNCTIONALITY_PROFILE_VIEW);
                }
            }
        });
    }


    /**
     * Устанавливает текущую функциональность фрагмента
     * @param Funcionality - статическое поле класса с тегом функциональности
     */
    private void setupFunctionality(String Funcionality) {

        sCurrentFunctionality = Funcionality; // выставляем текущую функциональность в зависимости от переданного аргумента

        switch (Funcionality) {
            case FUNCTIONALITY_PROFILE_VIEW: //режим просмотра
                List<String> ScreenData = getFieldsData(etViewsValues);
                if (!mUserProfileData.equals(ScreenData)) {  // если данные в EditView не совпадают с данными из модели UserPreferenses
                    userFields.saveUserProfileData(ScreenData); //то сохранить новые данные в модели
                    mUserProfileData = ScreenData; // заменить текущие данные в фрагменте
                    setFieldsData(txtViewsValues, mUserProfileData); // заполнить поля TextView
                };
                ButterKnife.apply(etViewsWrappers, INVISIBLE); //скрыть EditText
                ButterKnife.apply(txtViewsValues, VISIBLE); // показать TextView
                ButterKnife.apply(txtViewsLabels, VISIBLE);

                break;
            case FUNCTIONALITY_PROFILE_EDIT: //режим редактирования
                ButterKnife.apply(etViewsWrappers, VISIBLE); //показать EditText
                ButterKnife.apply(txtViewsValues, INVISIBLE); //скрыть TextView
                ButterKnife.apply(txtViewsLabels, INVISIBLE);
                break;
            default:
                sCurrentFunctionality = FUNCTIONALITY_PROFILE_VIEW;
                ButterKnife.apply(etViewsWrappers, INVISIBLE);
                ButterKnife.apply(txtViewsValues, VISIBLE);
                ButterKnife.apply(txtViewsLabels, VISIBLE);
                break;
        }

    }

    /**
     * переданные View установить как невидимые
     */
    static final ButterKnife.Action<View> INVISIBLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setVisibility(View.GONE);
        }
    };

    /**
     * переданные View установить как видимые
     */
    static final ButterKnife.Action<View> VISIBLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setVisibility(View.VISIBLE);
        }
    };

    /**
     * вставка массива значений в поля форм
     */
    private void setFieldsData(List<? extends TextView> viewList, List<String> userFields) {
        int i = 0;
        for (TextView viewField : viewList) {
            viewField.setText(userFields.get(i));
            i++;
        }
    }

    /**
     * Получение массива значений из полей форм
     */
    private List<String> getFieldsData(List<? extends TextView> viewList) {
        List<String> userFields = new ArrayList<String>();
        for (TextView viewField : viewList) {
            userFields.add(viewField.getText().toString());
        }
        return userFields;
    }

}
