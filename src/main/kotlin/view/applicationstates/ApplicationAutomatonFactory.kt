package view.applicationstates

import automatons.Automaton
import automatons.StandardAutomaton
import view.applicationstates.ApplicationStates.*
import view.applicationstates.ApplicationEvents.*

/**
 * Creates the application's state automaton.
 * @see Automaton
 */
class ApplicationAutomatonFactory {
	companion object {
		fun create() : Automaton {
			val res = StandardAutomaton()

			res
				.add(NO_FILE, SELECT_IMAGE, IMAGE_UNAPPLIED)
				.add(IMAGE_UNAPPLIED, SELECT_IMAGE, IMAGE_UNAPPLIED)
				.add(IMAGE_UNAPPLIED, CHANGE_PARAMETER, IMAGE_UNAPPLIED)
				.add(IMAGE_UNAPPLIED, APPLY_FILTER, IMAGE_APPLYING)
				.add(IMAGE_APPLYING, DONE, IMAGE_APPLIED)
				.add(IMAGE_APPLIED, SELECT_IMAGE, IMAGE_UNAPPLIED)
				.add(IMAGE_APPLIED, CHANGE_PARAMETER, IMAGE_APPLIED)
				.add(IMAGE_APPLIED, APPLY_FILTER, IMAGE_APPLYING)
				.add(IMAGE_APPLIED, EXPORT, IMAGE_APPLIED)

			return res
		}
	}
}