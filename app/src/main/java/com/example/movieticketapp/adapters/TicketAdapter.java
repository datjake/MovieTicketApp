package com.example.movieticketapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieticketapp.R;
import com.example.movieticketapp.models.Ticket;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    private List<Ticket> ticketList;

    public TicketAdapter(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        holder.tvTicketId.setText("Mã vé: " + ticket.getTicketId());
        holder.tvSeat.setText("Ghế: " + ticket.getSeatNumber());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTicketId, tvSeat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTicketId = itemView.findViewById(R.id.txtTicketId);
            tvSeat = itemView.findViewById(R.id.txtSeatNumber);
        }
    }
}