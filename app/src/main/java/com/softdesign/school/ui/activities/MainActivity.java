package com.softdesign.school.ui.activities;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingsFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.ImageHelper;
import com.softdesign.school.utils.Lg;



/* Имплементация слушателя события Click по элементу View */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int INT = 25;
    /**
     * Объявление view-элемента для дальнейшего обращения к нему
     */
    private CheckBox mCheckBox;
    private EditText mEditText;
    private EditText mEditText2;

    private Button mBtnBlue;
    private Button mBtnGreen;
    private Button mBtnRed;

    private Window mWindow;
    private Toolbar mToolbar;
    private Integer mToolbarColor = R.color.colorPrimary;
    private Integer mStatusColor = R.color.colorPrimaryDark;

    private DrawerLayout mNavigationDrawer; // Обертка меню навигации
    private NavigationView mNavigationView; // Здесь подключается элементы меню drawer_menu.xml, включая navigation_header.xml

    private Fragment mFragment;
    private FrameLayout mFrameContainer;

    public AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private View mHeaderLayout;

    AppBarLayout.LayoutParams params = null;

    /* Статическая переменная, в которой хранится состояние скрытого/видимого второго input (см. outState) */
    private static final String VISIBLE_KEY = "visible";
    private static final String TOOLBAR_COLOR_KEY = "toolbar";
    private static final String STATUSBAR_COLOR_KEY = "statusbar";

    private ImageView iv;
    private Bitmap avaBit;
    private Drawable avaDraw;


    /* Bundle - это переменная окружения, хранящая ключ-занчение (для сохранения состояния view-элементов активити при повороте экрана и других действий)
    Только для хранения маленьких значений!
    Если есть идентификатор, то при повороте экрана значения сохранятся за счет 2-х внутренних методов (см. ниже onSaveInstanceState). */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Задаем свой заголовок в ActionBar |
        ToolBar - это тот же ActionBar, но представленный в виде view-элемента в xml
        (см. res/values/styles.xml - необходимо наследоваться от Theme.AppCompat.Light.NoActionBar (текущая тема Theme.AppCompat.Light.DarkActionBar)) */
        setTitle("Lesson 4");

        Lg.e(this.getLocalClassName(), "on create"); /* Сообщение в логгер о создании главного активити */


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mWindow = (Window) getWindow();

        /* Меню навигации */
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mAppBar = (AppBarLayout) findViewById(R.id.appbar_layout);

        setupToolbar(); // 15.02.16
        setupDrawer();

        /* Проверка, запускается активити в первый раз или нет */
        if (savedInstanceState != null) { // не первый раз
/*            int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
            mEditText.setVisibility(visibleState);*/

        } else { // в первый раз, значит нужно загрузить первый фрагмент
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, new ProfileFragment()).commit(); // из activity_main.xml
            mNavigationView.getMenu().findItem(R.id.drawer_profile).setCheckable(true);
            mNavigationView.setCheckedItem(R.id.drawer_profile);
        }


    }


    /* Инициализация меню навигации */
    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_profile);
//                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_contacts);
//                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_team);
//                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.drawer_tascs:
                        mFragment = new TasksFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_tascs).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_tascs);
//                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_settings:
                        mFragment = new SettingsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_settings).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_settings);
//                        Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                        break;
                }

                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, mFragment).addToBackStack(null).commit(); // из activity_main.xml
//                    Lg.e("TAG", String.valueOf(item.getItemId()));
//                    Lg.e("TAG", String.valueOf(item.getMenuInfo()));

                }

                mNavigationDrawer.closeDrawers();
                return false;
            }

        });
    }

    public void lockAppBar(boolean collapse) {

        // Получаем параметры АппБара для Коллапсинга
        params = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();

        if (collapse) {

            // рограммно сворачивается АппБар
            mAppBar.setExpanded(false);

            // Здесь обрабатывается изменение высоты АппБара
            AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {

                // На вход обработчика подается АппБар и вертикальное смещение
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    // Сравнение высот Тулбара+вертикальное_смещение и Минимальная_высота_Тулбара+высота_СтатусБара (расчет в пикселях!)
                    if (mCollapsingToolbar.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbar) + getStatusBarHeight()) {
                        // Сворачивается АппБар и не реагирует на swipeDown
                        params.setScrollFlags(0);
                        mCollapsingToolbar.setLayoutParams(params);
                        // Отвязывается слушатель после манипуляций выше
                        mAppBar.removeOnOffsetChangedListener(this);
                    }
                }
            };
            mAppBar.addOnOffsetChangedListener(mListener);
            /*AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (mCollapsingToolbar.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbar) + getStatusBarHeight()) {
                        params.setScrollFlags(0);
                        mCollapsingToolbar.setLayoutParams(params);
                        mAppBar.removeOnOffsetChangedListener(this);
                    }
                }
            };*/

            // Снимаем все атрибуты, которые связаны со скроллингом
            /*params.setScrollFlags(0);
            mCollapsingToolbar.setLayoutParams(params);*/
        } else {
            mAppBar.setExpanded(true);
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
            mCollapsingToolbar.setLayoutParams(params);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    result = getResources().getDimensionPixelSize(resourceId);
                }
        return result;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
            finish();
            System.exit(0);
        }
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            Lg.e(this.getLocalClassName(), String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));

            getSupportFragmentManager().popBackStackImmediate();
//            Lg.e(this.getLocalClassName(), String.valueOf(getTitle()));
            switch (String.valueOf(getTitle())) {
                case "Профиль":
                    mNavigationView.getMenu().findItem(R.id.drawer_profile).setCheckable(true);
                    mNavigationView.setCheckedItem(R.id.drawer_profile);
                    break;
                case "Контакты":
                    mNavigationView.getMenu().findItem(R.id.drawer_contacts).setCheckable(true);
                    mNavigationView.setCheckedItem(R.id.drawer_contacts);
                    break;
                case "Команда":
                    mNavigationView.getMenu().findItem(R.id.drawer_team).setCheckable(true);
                    mNavigationView.setCheckedItem(R.id.drawer_team);
                    break;
                case "Задачи":
                    mNavigationView.getMenu().findItem(R.id.drawer_tascs).setCheckable(true);
                    mNavigationView.setCheckedItem(R.id.drawer_tascs);
                    break;
                case "Настройки":
                    mNavigationView.getMenu().findItem(R.id.drawer_settings).setCheckable(true);
                    mNavigationView.setCheckedItem(R.id.drawer_settings);
                    break;


            }
//            Toast.makeText(this, getSupportFragmentManager().getBackStackEntryCount(), Toast.LENGTH_SHORT).show();
        }
        else {
            super.onBackPressed();
        }
    }

    /* Инициализация тулбара после танцев со стилями */
    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        /* Если экшнбар поддерживается, не нулл */
        if (actionBar != null) {
            /* Чтобы появился сэндвич в тулбаре */
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    /* Метод для оперирования с items в опциях меню тулбара */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            /*Toast.makeText(this, "Menu click", Toast.LENGTH_SHORT).show();*/

            /* Обработчик клика по сэндвичу */
            mNavigationDrawer.openDrawer(GravityCompat.START); // Открытие меню навигации слева (START). Справа (END).
        }
        return super.onOptionsItemSelected(item);
    }

    /* Alt+Ins -> Implements Methods -> onClick(View v) */
    @Override
    public void onClick(View v) {
        /* Получаем id кликнутого элемента активити */
        int id = v.getId();
        switch (id) {
            /* Обработка клика по CheckBox */
            case R.id.checkBox:
                /* this - это текущее активити в качестве контекста для Toast */
                Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
                /* По клику на чекбокс скрываем или показываем второй input */
                if (mCheckBox.isChecked()) {
                    mEditText2.setVisibility(View.INVISIBLE);
                } else {
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
            /* Обработка клика синей кнопки */
            case R.id.btn_blue:
                setmToolbarColor(R.color.toolbar_blue);
                setmStatusColor(R.color.statusbar_darkblue);
                break;
            case R.id.btn_green:
                setmToolbarColor(R.color.toolbar_green);
                setmStatusColor(R.color.statusbar_darkgreen);
                break;
            case R.id.btn_red:
                setmToolbarColor(R.color.toolbar_red);
                setmStatusColor(R.color.statusbar_darkred);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getLocalClassName(), "on start");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getLocalClassName(), "on resume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getLocalClassName(), "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getLocalClassName(), "on stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getLocalClassName(), "on restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getLocalClassName(), "on destroy");
    }

    /* За счет этого метода происходит сохранение значений в Bundle для view-элементов, не имеющих идентификатор */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getLocalClassName(), "on Save Instance State");
        /* Сохранение в Bundle текущего состояния */
        /*outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
        outState.putInt(TOOLBAR_COLOR_KEY, mToolbarColor);
        outState.putInt(STATUSBAR_COLOR_KEY, mStatusColor);*/
    }

    /* За счет этого метода происходит восстановление значений из Bundle для view-элементов, не имеющих идентификатор */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        /* Получение из Bundle бывшего состояния */
        int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;

        /* Задаем второму input полученное состояние из Bundle */
        /*mEditText2.setVisibility(visibleState);

        setmToolbarColor(savedInstanceState.getInt(TOOLBAR_COLOR_KEY));

        setmStatusColor(savedInstanceState.getInt(STATUSBAR_COLOR_KEY));*/

    }

    private void setmToolbarColor(Integer color) {
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, color));
        mToolbarColor = color;
    }

    private void setmStatusColor(Integer color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Lg.e(this.getLocalClassName(), "on Set Status Bar Color: " + color);
            mWindow.setStatusBarColor(ContextCompat.getColor(this, color));
            mStatusColor = color;
        }
    }

}
