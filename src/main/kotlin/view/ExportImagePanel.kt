package view

import model.applicationfunctions.SwingModel
import java.awt.FlowLayout
import java.awt.GridLayout
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import javax.swing.*

class ExportImagePanel(private val model: SwingModel):JPanel(),PropertyChangeListener,UpdatableComponent {

	private val exportedFilePathTextField = createExportedFilePathTextField()
	private val pickExportDirectoryButton = createPickExportDirectoryButton()
	private val autoGeneratePathCheckBox = createAutoGeneratePathButton()
	private val exportImageButton = createExportImageButton()

	private var checkedAutoGeneratePath = true

	init {
		model.addPropertyChangeListener(this)
		layout = GridLayout(4,1)

		add(exportedFilePathTextField)
		add(pickExportDirectoryButton)
		add(createAutoPathPanel())
		add(exportImageButton)

		update()
	}

	private fun createExportedFilePathTextField():JTextField {
		val res = JTextField()
		res.isEnabled = false

		return res
	}

	private fun createPickExportDirectoryButton():JButton {
		val res = JButton(
			StringsManager.get("pick_output_directory")
		)

		return res
	}

	private fun createAutoGeneratePathButton():JCheckBox {
		val res = JCheckBox()

		res.isEnabled = true

		res.addActionListener {
			checkedAutoGeneratePath = res.isSelected
			update()
		}

		return res
	}

	private fun createExportImageButton(): JButton {
		val res = JButton(StringsManager.get("export_image"))

		res.isEnabled = true

		res.addActionListener {
			model.saveFilteredWithTvImage()
			//println("ExportButton pressed.")
		}

		return res
	}

	private fun createAutoPathPanel():JPanel {
		val res = JPanel()
		res.layout = FlowLayout()
		res.add(autoGeneratePathCheckBox)
		res.add(
			JLabel(
				StringsManager.get("option_auto_generate_output_path")
			)
		)
		return res
	}
	override fun propertyChange(evt: PropertyChangeEvent) {
		val propertiesToUpdateOn = listOf("outputPath","filteredWithTvImage")
		if (evt.propertyName in propertiesToUpdateOn) {
			update()
		}
	}

	override fun update() {
		exportedFilePathTextField.text = if (model.getOutputPath() != "") {
			model.getOutputPath()
		}else{
			StringsManager.get("no_output_path_selected")
		}
		autoGeneratePathCheckBox.isSelected = checkedAutoGeneratePath
		pickExportDirectoryButton.isEnabled = !checkedAutoGeneratePath
		exportImageButton.isEnabled = model.getFilteredWithTvImage() != null
	}

}