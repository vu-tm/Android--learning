package com.example.khtt.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khtt.KhachHangAdapter;
import com.example.khtt.data.KhachHang;
import com.example.khtt.databinding.FragmentListBinding;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentListBinding binding;

    private ActivityResultLauncher<Intent> pickXmlLauncher;
    private DashboardViewModel dashboardViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pickXmlLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            Uri fileUri = data.getData();
                            importKhachHangFromXml(fileUri);
                        }
                    }
                }
        );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        KhachHangAdapter adapter = new KhachHangAdapter();
        binding.recyclerView.setAdapter(adapter);

        dashboardViewModel =
                new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(DashboardViewModel.class);

        dashboardViewModel.getKhachHangList().observe(getViewLifecycleOwner(), adapter::setData);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false; // we are not supporting move
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getBindingAdapterPosition();
                        KhachHang kh = adapter.getKhachHangAt(position);
                        adapter.removeAt(position);

                        dashboardViewModel.deleteKhachHang(kh);
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);

        binding.exportButton.setOnClickListener(v -> {
            List<KhachHang> khList = adapter.getKhachHangList();
            File file = exportKhachHangToXml(khList, requireContext());
            sendXmlViaEmail(file, requireContext());
        });

        binding.importButton.setOnClickListener(v -> openFilePicker());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        pickXmlLauncher.launch(intent);
    }

    private void importKhachHangFromXml(Uri fileUri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(fileUri);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStream, null);

            List<KhachHang> khList = new ArrayList<>();
            KhachHang currentKh = null;
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tagName = parser.getName();
                        if ("khachhang".equals(tagName)) {
                            currentKh = new KhachHang();
                        } else if (currentKh != null) {
                            switch (tagName) {
                                case "phone":
                                    currentKh.setSdt(parser.nextText());
                                    break;
                                case "points":
                                    String pointsText = parser.nextText();
                                    currentKh.setPoints(pointsText.isEmpty() ? 0 : Integer.parseInt(pointsText));
                                    break;
                                case "ngaytao":
                                    currentKh.setCreateDate(parser.nextText());
                                    break;
                                case "ngaychinhsua":
                                    currentKh.setModifyDate(parser.nextText());
                                    break;
                                case "note":
                                    currentKh.setNote(parser.nextText());
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if ("khachhang".equals(tagName) && currentKh != null) {
                            khList.add(currentKh);
                        }
                        break;
                }
                eventType = parser.next();
            }

            assert inputStream != null;
            inputStream.close();

            // Insert/update into database
            for (KhachHang kh : khList) {
                dashboardViewModel.saveOrUpdateKhachHang(
                        kh.getSdt(),
                        kh.getPoints(),
                        kh.getCreateDate(),
                        kh.getModifyDate(),
                        kh.getNote()
                );
            }

            Toast.makeText(getContext(), "Import thành công: " + khList.size() + " khách hàng", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File exportKhachHangToXml(List<KhachHang> khList, Context context) {

        File file = new File(context.getFilesDir(), "KhachHang.xml");
        /*File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!downloadsDir.exists()) downloadsDir.mkdirs();

        File file = new File(downloadsDir, "KhachHang.xml");
*/
        try (FileOutputStream fos = new FileOutputStream(file)) {
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "danhsachkhachhang");

            for (KhachHang kh : khList) {
                serializer.startTag("", "khachhang");

                serializer.startTag("", "phone");
                serializer.text(kh.getSdt());
                serializer.endTag("", "phone");

                serializer.startTag("", "points");
                serializer.text(String.valueOf(kh.getPoints()));
                serializer.endTag("", "points");

                serializer.startTag("", "ngaytao");
                serializer.text(kh.getCreateDate());
                serializer.endTag("", "ngaytao");

                serializer.startTag("", "ngaychinhsua");
                serializer.text(kh.getModifyDate());
                serializer.endTag("", "ngaychinhsua");

                serializer.startTag("", "note");
                serializer.text(kh.getNote());
                serializer.endTag("", "note");

                serializer.endTag("", "khachhang");
            }

            serializer.endTag("", "danhsachkhachhang");
            serializer.endDocument();
            serializer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public void sendXmlViaEmail(File xmlFile, Context context) {
        if (xmlFile == null || !xmlFile.exists()) return;

        Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", xmlFile);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/xml");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Danh sách khách hàng");
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        context.startActivity(Intent.createChooser(emailIntent, "Gửi xml qua email"));
    }

}