package com.example.firebase_login_signup_form

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.firebase_login_signup_form.databinding.FragmentLoginBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*

class LoginFragment : Fragment() {

    lateinit var loginBinding: FragmentLoginBinding
    private lateinit var email: String
    private lateinit var pwd: String
    private lateinit var fAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var callbackManager: CallbackManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)

        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fAuth = FirebaseAuth.getInstance()

        // Creates a button that mimics a crash when pressed
//        loginBinding.crashBtn.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
        loginBinding.signinBtn.setOnClickListener {

            email = loginBinding.et1.text.toString()
            pwd = loginBinding.et2.text.toString()

            if (email.isEmpty()) {
                loginBinding.et1.error = "Email must not be empty"
                return@setOnClickListener
            }

            if (pwd.isEmpty() || pwd.length < 6) {
                loginBinding.et2.error = "Password must not be empty"
                return@setOnClickListener
            }

            loginBinding.progressBar2.visibility = View.VISIBLE

            fAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(requireContext() as Activity) { task ->
                    if (task.isSuccessful) {
                        loginBinding.progressBar2.visibility = View.INVISIBLE
                        findNavController().navigate(R.id.action_LoginFragment_to_dashboardFragment)

                    } else {
                        loginBinding.progressBar2.visibility = View.INVISIBLE
                        loginBinding.et1.setText("")
                        loginBinding.et2.setText("")
                        Toast.makeText(requireContext(), "Invalid Email/Password",
                            Toast.LENGTH_LONG).show()
                    }
                }
        }

        loginBinding.text5.setOnClickListener {
            Log.d("ACTION", "navigate")
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }

        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        loginBinding.btnGoogle.setOnClickListener {
            loginBinding.progressBar2.visibility = View.VISIBLE
            googleLoginProcess()
            loginBinding.progressBar2.visibility = View.INVISIBLE

        }

        loginBinding.btnFacebook.setOnClickListener {
            loginBinding.progressBar2.visibility = View.VISIBLE
            if (userLoggedIn()) {
                fAuth.signOut()
            } else {
                LoginManager.getInstance()
                    .logInWithReadPermissions(this, listOf("public_profile", "email"))
            }
            facebookLoginProcess()
            loginBinding.progressBar2.visibility = View.INVISIBLE
        }

        loginBinding.btnTwitter.setOnClickListener {
            loginBinding.progressBar2.visibility = View.VISIBLE
            twitterLoginProcess()
            loginBinding.progressBar2.visibility = View.INVISIBLE
        }
    }

    private fun googleLoginProcess() {
        val signInIntent = mGoogleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result

            email = account?.email.toString()
            Toast.makeText(context, "Email ->$email", Toast.LENGTH_SHORT).show()

            if (account != null) {
                updateUI(account)
            } else {
                Toast.makeText(context, task.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        fAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                findNavController().navigate(R.id.action_LoginFragment_to_dashboardFragment)
            } else {
                Toast.makeText(context, it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun userLoggedIn(): Boolean {
        if (fAuth.currentUser != null && AccessToken.getCurrentAccessToken()!!.isExpired) {
            return true
        }
        return false
    }


    private fun facebookLoginProcess() {
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    Log.d(ContentValues.TAG, "facebook:onSuccess:$result")
                    handleFacebookAccessToken(result.accessToken)
                }

                override fun onCancel() {
                    Log.d(ContentValues.TAG, "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d(ContentValues.TAG, "facebook:onError", error)
                }
            })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("FacebookAccessToken", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        fAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireContext() as Activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    findNavController().navigate(R.id.action_LoginFragment_to_dashboardFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


    private fun twitterLoginProcess() {
        val provider = OAuthProvider.newBuilder("twitter.com")
        provider.addCustomParameter("lang", "eng")

        val pendingResultTask: Task<AuthResult>? = fAuth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask.addOnSuccessListener {
                Log.d("success", "Login Successful")
                findNavController().navigate(R.id.action_LoginFragment_to_dashboardFragment)
                Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
            }
                .addOnFailureListener {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    // Handle failure.
                }
        } else {

            try {
                fAuth.startActivityForSignInWithProvider(requireContext() as Activity,
                    provider.build())
                    .addOnSuccessListener {
                        findNavController().navigate(R.id.action_LoginFragment_to_dashboardFragment)
                        Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
                        Log.d("success", "Loggedin")
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        // Handle failure.
                    }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                Log.d("ERROR", "${e.message}")

            }
        }
    }
}