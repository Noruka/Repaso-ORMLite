package es.guillemburnleesviada.repasoormlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.guillemburnleesviada.repasoormlite.R;
import es.guillemburnleesviada.repasoormlite.pojo.Ordenador;

public class AdapterOrdenador extends RecyclerView.Adapter<AdapterOrdenador.Card> {

    private Context context;
    private int resource;
    private ArrayList<Ordenador> ordenadores;

    public AdapterOrdenador(Context context, int resource, ArrayList<Ordenador> ordenadores) {
        this.context = context;
        this.resource = resource;
        this.ordenadores = ordenadores;
    }

    @NonNull
    @Override
    public Card onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View cardView = inflater.inflate(resource, null);
        Card card = new Card(cardView);

        return card;
    }

    @Override
    public void onBindViewHolder(@NonNull Card holder, int position) {
        Ordenador ordenador = ordenadores.get(position);
        holder.txtModelo.setText(ordenador.getModelo());
        holder.txtMarca.setText(ordenador.getMarca());
        holder.txtRAM.setText(ordenador.getRam()+" GB");
        holder.txtHD.setText(ordenador.getHd()+" TB");
        holder.rtVal.setRating(ordenador.getValoracion());
        holder.rtVal.setIsIndicator(true);
    }

    @Override
    public int getItemCount() {
        return ordenadores.size();
    }

    class Card extends RecyclerView.ViewHolder{

        private TextView txtMarca, txtModelo, txtRAM, txtHD;
        private RatingBar rtVal;

        public Card(@NonNull View itemView){
            super(itemView);
            txtMarca = itemView.findViewById(R.id.txtMarcaCard);
            txtModelo = itemView.findViewById(R.id.txtModeloCard);
            txtRAM = itemView.findViewById(R.id.txtRamCard);
            txtHD = itemView.findViewById(R.id.txtHdCard);
            rtVal = itemView.findViewById(R.id.rtValCard);
        }
    }

}
