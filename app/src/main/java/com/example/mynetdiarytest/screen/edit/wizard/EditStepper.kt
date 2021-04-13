package com.example.mynetdiarytest.screen.edit.wizard

class EditStepper {

    private val steps: MutableList<EditStep> = mutableListOf()
    private var currentStepIndex: Int = 0

    fun init(steps: List<EditStep>) {
        this.steps.addAll(steps)
    }

    fun moveToFirstStep(
        onSuccess: (EditStep) -> Unit,
        onError: () -> Unit
    ) {
        if (isEmpty()) {
            onError()
        } else {
            currentStepIndex = 0
            onSuccess(getCurrentStep())
        }
    }

    fun moveToPreviousStep(
        onSuccess: (EditStep) -> Unit,
        onError: () -> Unit
    ) {
        if (isFirstStep()) {
            onError()
        } else {
            currentStepIndex--
            onSuccess(getCurrentStep())
        }
    }

    fun moveToNextStep(
        onSuccess: (EditStep) -> Unit,
        onError: () -> Unit
    ) {
        if (isLastStep()) {
            onError()
        } else {
            currentStepIndex++
            onSuccess(getCurrentStep())
        }
    }

    private fun getCurrentStep(): EditStep = steps[currentStepIndex]

    private fun isFirstStep(): Boolean = currentStepIndex == 0

    private fun isLastStep(): Boolean = currentStepIndex == steps.lastIndex

    private fun isEmpty(): Boolean = steps.isEmpty()
}