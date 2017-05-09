package chickenzero.ht.com.lienquan.customize.SectionRecycleView;

interface ItemProvider {

  int getSectionCount();

  int getItemCount(int sectionIndex);

  boolean showHeadersForEmptySections();
}
