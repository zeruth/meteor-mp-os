package nulled

import com.openosrs.injector.Injector
import meteor.Logger
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.IOException

open class InjectTask : DefaultTask() {
    @InputDirectory
    lateinit var api: String
    @InputFile
    lateinit var mixins: String
    @InputFile
    lateinit var target: String
    @OutputFile
    lateinit var output: String

    @TaskAction
    @Throws(IOException::class)
    fun inject() {
        try {
            Injector.api = api
            Injector.mixinsFile = mixins
            Injector.target = target
            Injector.output = output
            File(System.getProperty("user.home") + "/.meteor/logs/").mkdirs()
            Logger.logFile = File(System.getProperty("user.home") + "/.meteor/logs/injector.txt")
            Injector.main(null)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}