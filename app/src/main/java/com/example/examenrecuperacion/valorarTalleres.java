package com.example.examenrecuperacion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link valorarTalleres.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link valorarTalleres#newInstance} factory method to
 * create an instance of this fragment.
 */
public class valorarTalleres extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    //private String mParam2;

    EditText item1, item2;
    Button subir;

    private OnFragmentInteractionListener mListener;

    public valorarTalleres() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment valorarTalleres.
     */
    // TODO: Rename and change types and number of parameters
    public static valorarTalleres newInstance(String param1) {
        valorarTalleres fragment = new valorarTalleres();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_valorar_talleres,container,false
        );

        // Me conecto a la bbdd Firebase
        final FirebaseDatabase db;

        // APunto a mi proyecto
        db = FirebaseDatabase.getInstance();

        item1 = view.findViewById(R.id.valoracion);
        item2 = view.findViewById(R.id.comentarios);
        subir = view.findViewById(R.id.subir);




        //Generamos un Listener para cuando clickamos el boton Enviar
        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final valoraciones inf = new valoraciones(item1.getText().toString(),item2.getText().toString());
                db.getReference().child(mParam1).push().setValue(inf);
                Toast.makeText(getActivity(), "Info guardada", Toast.LENGTH_SHORT).show();
                clearEditFields();

            }
        });


        return view;

    }
    //Funcion para limpiar los campos para realizar una nueva reserva
    public void clearEditFields() {
        SystemClock.sleep(500);
        item1.getText().clear();
        item2.getText().clear();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
