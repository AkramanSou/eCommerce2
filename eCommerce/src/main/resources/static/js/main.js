// ── Boutons de filtre (catégories) ──
document.querySelectorAll('.filter-btn').forEach(btn => {
  btn.addEventListener('click', function () {
    this.closest('.d-flex').querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
    this.classList.add('active');
  });
});

// ── Boutons taille produit ──
document.querySelectorAll('.size-btn').forEach(btn => {
  btn.addEventListener('click', function () {
    this.closest('.d-flex').querySelectorAll('.size-btn').forEach(b => b.classList.remove('active'));
    this.classList.add('active');
  });
});

// ── Panier ── (compteur simple en session)
let cartCount = parseInt(sessionStorage.getItem('cartCount') || '0');
updateCartDisplay();

document.querySelectorAll('.btn-cart').forEach(btn => {
  btn.addEventListener('click', function () {
    cartCount++;
    sessionStorage.setItem('cartCount', cartCount);
    updateCartDisplay();
    // Feedback visuel
    this.innerHTML = '<i class="bi bi-check"></i>';
    this.style.background = '#27ae60';
    setTimeout(() => {
      this.innerHTML = '<i class="bi bi-bag-plus"></i>';
      this.style.background = '';
    }, 1000);
  });
});

function updateCartDisplay() {
  const badge = document.querySelector('.cart-count');
  if (badge) {
    badge.textContent = cartCount;
    badge.style.display = cartCount > 0 ? 'flex' : 'none';
  }
}
