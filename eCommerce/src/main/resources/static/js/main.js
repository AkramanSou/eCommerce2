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

// ── PANIER ──
let cart = JSON.parse(sessionStorage.getItem('cart') || '[]');
updateCartDisplay();

document.querySelectorAll('.btn-cart').forEach(btn => {
  btn.addEventListener('click', function () {
    // Récupérer les infos du produit
    const card = this.closest('.product-card, .promo-card');
    const nom = card.querySelector('.product-name')?.textContent || 'Produit';
    const prix = card.querySelector('.price')?.textContent || '0 €';
    const icone = card.querySelector('.product-img')?.textContent?.trim().charAt(0) || '🥊';

    // Vérifier si déjà dans le panier
    const existant = cart.find(p => p.nom === nom);
    if (existant) {
      existant.quantite++;
    } else {
      cart.push({ nom, prix, icone, quantite: 1 });
    }

    sessionStorage.setItem('cart', JSON.stringify(cart));
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
  const total = cart.reduce((sum, p) => sum + p.quantite, 0);
  const badge = document.querySelector('.cart-count');
  if (badge) {
    badge.textContent = total;
    badge.style.display = total > 0 ? 'flex' : 'none';
  }

  // Si on est sur la page panier
  const panierVide = document.getElementById('panier-vide');
  const panierContenu = document.getElementById('panier-contenu');
  if (!panierVide || !panierContenu) return;

  if (cart.length === 0) {
    panierVide.style.display = 'block';
    panierContenu.style.display = 'none !important';
    return;
  }

  // Afficher le contenu
  panierVide.style.display = 'none';
  panierContenu.style.removeProperty('display');
  panierContenu.style.display = 'flex';

  // Générer les articles
  const articlesContainer = panierContenu.querySelector('.col-lg-8');
  let html = '<h2 class="section-title mb-4">Articles</h2>';
  let total_prix = 0;

  cart.forEach((produit, index) => {
    const prixNum = parseFloat(produit.prix.replace(',', '.').replace(' €', '').replace('€', ''));
    const sousTotal = (prixNum * produit.quantite).toFixed(2).replace('.', ',');
    total_prix += prixNum * produit.quantite;

    html += `
      <div class="cart-item">
        <div class="cart-item-img">${produit.icone}</div>
        <div class="flex-grow-1">
          <div class="fw-bold">${produit.nom}</div>
          <div class="text-muted" style="font-size:0.85rem;">${produit.prix} / unité</div>
        </div>
        <div class="d-flex align-items-center gap-2">
          <button class="qty-btn" onclick="changerQte(${index}, -1)">−</button>
          <span class="fw-bold px-2">${produit.quantite}</span>
          <button class="qty-btn" onclick="changerQte(${index}, +1)">+</button>
        </div>
        <div class="fw-bold" style="min-width:70px;text-align:right;">${sousTotal} €</div>
        <button class="btn btn-sm" style="color:#aaa;" onclick="supprimerArticle(${index})">
          <i class="bi bi-trash"></i>
        </button>
      </div>`;
  });

  articlesContainer.innerHTML = html;

  // Mettre à jour le récap
  const totalFormate = total_prix.toFixed(2).replace('.', ',');
  const livraison = total_prix >= 80 ? 'Offerte' : '5,99 €';
  panierContenu.querySelector('.col-lg-4 div').innerHTML = `
    <h5 class="fw-bold mb-3">Récapitulatif</h5>
    <div class="d-flex justify-content-between mb-2" style="font-size:0.9rem;"><span>Sous-total</span><span>${totalFormate} €</span></div>
    <div class="d-flex justify-content-between mb-3" style="font-size:0.9rem;"><span>Livraison</span><span class="text-success fw-semibold">${livraison}</span></div>
    <hr/>
    <div class="d-flex justify-content-between mb-4 fw-bold"><span>Total</span><span style="color:var(--rouge);">${totalFormate} €</span></div>
    <a href="#" class="btn-rouge d-block text-center">Commander →</a>
    <a href="/boutique" class="d-block text-center mt-2" style="font-size:0.85rem;color:#888;text-decoration:none;">← Continuer mes achats</a>
  `;
}

function changerQte(index, delta) {
  cart[index].quantite += delta;
  if (cart[index].quantite <= 0) cart.splice(index, 1);
  sessionStorage.setItem('cart', JSON.stringify(cart));
  updateCartDisplay();
}

function supprimerArticle(index) {
  cart.splice(index, 1);
  sessionStorage.setItem('cart', JSON.stringify(cart));
  updateCartDisplay();
}