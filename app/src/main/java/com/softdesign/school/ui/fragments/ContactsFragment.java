package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;

public class ContactsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /* Надуваем из xml лэйаута view элемент */
        View convertView = inflater.inflate(R.layout.fragment_contacts, null, false);

        /* Задаем в тулбаре выбранное в меню навигации наименование */
        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));

        return convertView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
