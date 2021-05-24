package com.dagogue.learnit.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dagogue.learnit.R
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var textViewLowerCase: TextView
    private lateinit var textViewUpperCase: TextView
    private lateinit var letterButton: AppCompatImageView
    private lateinit var submitAnswerButton: AppCompatImageView
    private lateinit var context: Activity

    private lateinit var speechRecognizerIntent: Intent

    companion object {
        const val code = 1
        const val POSITION_EXTRA = "POSITION_EXTRA"
        fun newInstance(position: Int): HomeFragment {
            val bundle = Bundle()
            bundle.putInt(POSITION_EXTRA, position)
            val fragment = HomeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val FRANCE = inflater.inflate(R.layout.fragment_home, container, false)
        textViewLowerCase = FRANCE.findViewById(R.id.lowerCaseLetter)
        textViewUpperCase = FRANCE.findViewById(R.id.upperCaseLetter)
        letterButton = FRANCE.findViewById(R.id.answer)
        submitAnswerButton = FRANCE.findViewById(R.id.submit)
        submitAnswerButton.setOnClickListener(View.OnClickListener {
            listen()
            //startListening()
        })
        letterButton.setOnClickListener(View.OnClickListener {
            // promptForGame("Li la lettre qui est affichée")
            speakLetter(textViewLowerCase.text) {

            }
        })
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            // textView.text = it
        })
        Glide.with(this).load(R.drawable.micro2)
            .circleCrop()
            .into(submitAnswerButton)

        Glide.with(this).load(R.drawable.speaker)
            .circleCrop()
            .into(letterButton)


        initData()
        return FRANCE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTextToSpeech()
    }


    private fun initData() {
        Log.d("position", arguments?.getInt(POSITION_EXTRA).toString())
        val pos = arguments?.getInt(POSITION_EXTRA)
        when (pos) {
            0 -> {
                textViewLowerCase.text = "a".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "a".toUpperCase(Locale.FRANCE)
            }
            1 -> {
                textViewLowerCase.text = "b".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "b".toUpperCase(Locale.FRANCE)
            }
            2 -> {
                textViewLowerCase.text = "c".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "c".toUpperCase(Locale.FRANCE)
            }
            3 -> {
                textViewLowerCase.text = "d".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "d".toUpperCase(Locale.FRANCE)
            }
            4 -> {
                textViewLowerCase.text = "e".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "e".toUpperCase(Locale.FRANCE)
            }
            5 -> {
                textViewLowerCase.text = "f".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "f".toUpperCase(Locale.FRANCE)
            }
            6 -> {
                textViewLowerCase.text = "g".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "g".toUpperCase(Locale.FRANCE)
            }
            7 -> {
                textViewLowerCase.text = "h".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "h".toUpperCase(Locale.FRANCE)
            }
            8 -> {
                textViewLowerCase.text = "i".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "i".toUpperCase(Locale.FRANCE)
            }
            9 -> {
                textViewLowerCase.text = "j".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "j".toUpperCase(Locale.FRANCE)
            }
            10 -> {
                textViewLowerCase.text = "k".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "k".toUpperCase(Locale.FRANCE)
            }
            11 -> {
                textViewLowerCase.text = "l".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "l".toUpperCase(Locale.FRANCE)
            }
            12 -> {
                textViewLowerCase.text = "m".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "m".toUpperCase(Locale.FRANCE)
            }
            13 -> {
                textViewLowerCase.text = "n".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "n".toUpperCase(Locale.FRANCE)
            }
            14 -> {
                textViewLowerCase.text = "o".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "o".toUpperCase(Locale.FRANCE)
            }
            15 -> {
                textViewLowerCase.text = "p".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "p".toUpperCase(Locale.FRANCE)
            }
            16 -> {
                textViewLowerCase.text = "q".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "q".toUpperCase(Locale.FRANCE)
            }
            17 -> {
                textViewLowerCase.text = "r".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "r".toUpperCase(Locale.FRANCE)
            }
            18 -> {
                textViewLowerCase.text = "s".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "s".toUpperCase(Locale.FRANCE)
            }
            19 -> {
                textViewLowerCase.text = "t".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "t".toUpperCase(Locale.FRANCE)
            }
            20 -> {
                textViewLowerCase.text = "u".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "u".toUpperCase(Locale.FRANCE)
            }
            21 -> {
                textViewLowerCase.text = "v".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "v".toUpperCase(Locale.FRANCE)
            }
            22 -> {
                textViewLowerCase.text = "w".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "w".toUpperCase(Locale.FRANCE)
            }
            23 -> {
                textViewLowerCase.text = "x".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "x".toUpperCase(Locale.FRANCE)
            }
            24 -> {
                textViewLowerCase.text = "y".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "y".toUpperCase(Locale.FRANCE)
            }
            25 -> {
                textViewLowerCase.text = "z".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "z".toUpperCase(Locale.FRANCE)
            }

            else -> { // Note the block
                textViewLowerCase.text = "a".toLowerCase(Locale.FRANCE)
                textViewUpperCase.text = "a".toUpperCase(Locale.FRANCE)
            }
        }

        letterButton.callOnClick()
    }

    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(context, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                /* Toast.makeText(
                     context,
                     "Text-To-Speech engine is initialized", Toast.LENGTH_LONG
                 ).show()*/
                textToSpeech.language = Locale.FRANCE
                textToSpeech.setSpeechRate(0.3.toFloat())
                textToSpeech.setPitch(1.toFloat())
            } else if (it == TextToSpeech.ERROR) {
                Toast.makeText(
                    context,
                    "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG
                ).show();
            }
        })
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as Activity
    }
    fun listen() {
        speakLetter("Allez dis nous la lettre qui est affichée") {
            if (it) {
                startAudioListening()
            }
        }
    }

    private fun startAudioListening() {
        context.runOnUiThread {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.FRANCE)
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Prononcez le mot...")
            }
            speechRecognizer.setRecognitionListener(object : RecognitionListener {

                override fun onReadyForSpeech(p0: Bundle?) {
                    Log.d("onReadyForSpeech", "onReadyForSpeech")
                }

                override fun onBeginningOfSpeech() {

                }

                override fun onRmsChanged(p0: Float) {

                }

                override fun onBufferReceived(p0: ByteArray?) {

                }

                override fun onEndOfSpeech() {

                }

                override fun onError(p0: Int) {

                }

                override fun onResults(p0: Bundle?) {
                    var keeper = ""
                    val matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    matches?.let {
                        keeper = it[0]
                        Toast.makeText(context, keeper, Toast.LENGTH_LONG).show()
                        processAnswer(keeper)
                    }
                }

                override fun onPartialResults(p0: Bundle?) {

                }

                override fun onEvent(p0: Int, p1: Bundle?) {

                }

            })
            speechRecognizer.startListening(intent)
        }
    }

    fun speakLetter(textToBeSpoken: CharSequence = "", completion: (Boolean) -> Unit) {

        textToSpeech.setOnUtteranceProgressListener(object: UtteranceProgressListener() {
            override fun onStart(p0: String?) {
            }

            override fun onDone(p0: String?) {
                completion(true)
            }

            override fun onError(p0: String?) {
                completion(false)
            }
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val bundle = Bundle()

            textToSpeech.speak(textToBeSpoken, TextToSpeech.QUEUE_FLUSH, Bundle.EMPTY, "1")

        } else {
            val params = HashMap<String, String>()
            params[TextToSpeech.Engine.KEY_PARAM_VOLUME] = "10"

            textToSpeech.speak(textToBeSpoken.toString(), TextToSpeech.QUEUE_FLUSH, null)
        }


        //   startListening()
    }

    fun startListening() {
        //speakLetter("A ton tour je t'écoute")
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.FRANCE)
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Prononcez le mot...")
        }
        startActivityForResult(intent, code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> {
                val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
                //  Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                processAnswer(result)
            }
        }
    }

    private fun processAnswer(res: String?) {
        res?.let {
            val letter = it.first()
            if (letter.equals(textViewLowerCase.text.first(), true)) {
                speakLetter("Bravo"){

                }
            } else {
                speakLetter("Non essaie encore"){
                submitAnswerButton.callOnClick()
                }
            }
        }

    }

}