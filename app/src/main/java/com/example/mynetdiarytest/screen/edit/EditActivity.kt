package com.example.mynetdiarytest.screen.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.screen.BaseActivity
import com.example.mynetdiarytest.screen.edit.wizard.EditStep
import com.example.mynetdiarytest.screen.edit.wizard.EditStepper
import com.example.mynetdiarytest.screen.injectViewModel
import kotlinx.android.synthetic.main.a_edit.*
import timber.log.Timber
import javax.inject.Inject

class EditActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<EditViewModel>(viewModelFactory) }

    private val editStepper = EditStepper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setClient(intent.getParcelableExtra(CLIENT_KEY) ?: Client.EMPTY)

        setSupportActionBar(toolbar)

        editStepper.apply {
            init(createSteps())
            moveToFirstStep(
                onSuccess = { step -> showStep(step) },
                onError = { saveChanges() }
            )
        }

        back.setOnClickListener {
            editStepper.moveToPreviousStep(
                onSuccess = { step -> showStep(step) },
                onError = { discardChanges() }
            )
        }

        next.setOnClickListener {
            editStepper.moveToNextStep(
                onSuccess = { step -> showStep(step) },
                onError = { saveChanges() }
            )
        }
    }

    override fun subscribe() { }

    override fun getLayout(): Int = R.layout.a_edit

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }

    private fun saveChanges() {
        Timber.d("#saveChanges")

        viewModel.saveChanges()
        finish()
    }

    private fun discardChanges() {
        Timber.d("#discardChanges")
        finish()
    }

    private fun createSteps(): List<EditStep> =
        mutableListOf<EditStep>().apply {
            add(EditStep.BODY_WEIGHT)
            add(EditStep.DATE_OF_BIRTH)
            add(EditStep.PHOTO)
        }

    private fun showStep(step: EditStep) {
        when (step) {
            EditStep.BODY_WEIGHT -> {
                addFragment(BodyWeightFragment.getInstance())

                toolbar.title = getString(R.string.toolbar_body_weight)

                back.text = getString(R.string.back)
                next.text = getString(R.string.next)
            }
            EditStep.DATE_OF_BIRTH -> {
                addFragment(DateOfBirthFragment.getInstance())

                toolbar.title = getString(R.string.toolbar_date_of_birth)

                back.text = getString(R.string.back)
                next.text = getString(R.string.next)
            }
            EditStep.PHOTO -> {
                addFragment(PhotoFragment.getInstance())

                toolbar.title = getString(R.string.toolbar_photo)

                back.text = getString(R.string.back)
                next.text = getString(R.string.done)
            }
        }
    }

    private fun addFragment(fragment: Fragment, layoutId: Int = R.id.container) {
        supportFragmentManager.beginTransaction().add(layoutId, fragment).commit()
    }

    companion object {

        private const val CLIENT_KEY = "CLIENT_KEY"

        fun intentInstance(context: Context) = Intent(context, EditActivity::class.java)

        fun intentInstance(context: Context, client: Client) =
            Intent(context, EditActivity::class.java).apply {
                putExtra(CLIENT_KEY, client)
            }
    }
}